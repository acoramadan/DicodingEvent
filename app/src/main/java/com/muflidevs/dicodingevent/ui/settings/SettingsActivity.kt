package com.muflidevs.dicodingevent.ui.settings

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.switchmaterial.SwitchMaterial
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.databinding.ActivitySettingsBinding
import com.muflidevs.dicodingevent.ui.viewmodel.MainSettingsModel
import com.muflidevs.dicodingevent.ui.viewmodel.ViewModelSettingsFactory
import java.util.concurrent.TimeUnit

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var mainSettingsModel: MainSettingsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val switchNotif = findViewById<SwitchMaterial>(R.id.switch_notification)

        val pref = SettingPreferences.getInstance(application.dataStore)
        mainSettingsModel =
            ViewModelProvider(this, ViewModelSettingsFactory(pref))[MainSettingsModel::class.java]

        mainSettingsModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        mainSettingsModel.getNotificationSettings().observe(this) { isNotificationActive: Boolean ->
            if (isNotificationActive) {
                startPeriodicEventWorker()
                switchNotif.isChecked = true
            } else {
                stopPeriodicEventWorker()
                switchNotif.isChecked = false
            }
        }
        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainSettingsModel.saveThemeSettings(isChecked)
        }

        binding.switchNotification.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                startPeriodicEventWorker()
                mainSettingsModel.saveNotificationSettings(true)
            } else {
                stopPeriodicEventWorker()
                mainSettingsModel.saveNotificationSettings(false)
            }
        }

        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun startPeriodicEventWorker() {
        val eventWorkRequest = PeriodicWorkRequestBuilder<EventWorkerNotification>(1, TimeUnit.DAYS)
            .build()
        WorkManager.getInstance(this).enqueue(eventWorkRequest)
    }

    private fun stopPeriodicEventWorker() {
        WorkManager.getInstance(this).cancelAllWorkByTag("event_work")
    }
}
