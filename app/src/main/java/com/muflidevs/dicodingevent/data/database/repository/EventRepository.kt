package com.muflidevs.dicodingevent.data.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.muflidevs.dicodingevent.data.database.EventDAO
import com.muflidevs.dicodingevent.data.database.EventRoomDatabase
import com.muflidevs.dicodingevent.data.response.DetailData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EventRepository(application: Application) {
    private val mEventDao : EventDAO
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = EventRoomDatabase.getDatabase(application)
        mEventDao = db.eventDAO()
    }

    fun getAllEvent(): LiveData<List<DetailData>> = mEventDao.getAllEvents()

    fun insert(detailData: DetailData) {
        executorService.execute{mEventDao.insert(detailData)}
    }

    fun delete(detailData: DetailData) {
        executorService.execute{mEventDao.delete(detailData)}
    }
}