package com.muflidevs.dicodingevent.ui.settings

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import com.muflidevs.dicodingevent.data.remote.retrofit.ApiConfig
import com.muflidevs.dicodingevent.ui.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventWorkerNotification(ctx: Context, params: WorkerParameters) :
    CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            fetchAndNotify(applicationContext)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private suspend fun fetchAndNotify(context: Context) {
        withContext(Dispatchers.IO) {
            try {
                val response = ApiConfig.getApiService().getEvents(active = -1, limit = 1)
                val eventList = response.data

                if (eventList.isNotEmpty()) {
                    val event = eventList[0]
                    showNotification(context, event)
                } else {
                    Log.e("fetchAndNotify", "Error mengambil event data")
                }
            } catch (e: Exception) {
                Log.e("fetchAndNotify", "Error mengambil event data: ${e.message}")
            }
        }
    }

    private fun showNotification(context: Context, detailData: DetailData) {
        val channelId = "event_channel"
        val notificationId = 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Event Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = detailData.description
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, DetailActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("EXTRA_DETAIL", detailData)
        }

        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(detailData.ownerName)
            .setContentText(detailData.name)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@with
            }
            notify(notificationId, notificationBuilder.build())
        }
    }

}
