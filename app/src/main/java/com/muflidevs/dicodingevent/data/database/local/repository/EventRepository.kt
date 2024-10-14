package com.muflidevs.dicodingevent.data.database.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.muflidevs.dicodingevent.data.database.local.entity.DetailDataEntity
import com.muflidevs.dicodingevent.data.database.local.room.EventDAO
import com.muflidevs.dicodingevent.data.database.local.room.EventRoomDatabase
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EventRepository(application: Application) {
    private val mEventDao : EventDAO
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = EventRoomDatabase.getDatabase(application)
        mEventDao = db.eventDAO()
    }

    fun getAllEvent(): LiveData<List<DetailDataEntity>> = mEventDao.getAllEvents()

    fun insert(detailData: DetailDataEntity) {
        executorService.execute{mEventDao.insert(detailData)}
    }

    fun delete(detailData: DetailDataEntity) {
        executorService.execute{mEventDao.delete(detailData)}
    }
    fun getEventById(id : Int) : LiveData<DetailDataEntity> {
        return mEventDao.getEventById(id)
    }
    fun mapRemoteToLocal(detailData : DetailData) : DetailDataEntity {
        return DetailDataEntity(
            id = detailData.id,
            name = detailData.name,
            summary = detailData.summary,
            description = detailData.description,
            imageLogo = detailData.imageLogo,
            category = detailData.category,
            cityName = detailData.cityName,
            mediaCover = detailData.mediaCover,
            beginTime = detailData.beginTime,
            registrants = detailData.registrants,
            endTime = detailData.endTime,
            link = detailData.link
        )
    }
    fun mapRemoteToLocal(detailData : DetailDataEntity) : DetailDataEntity {
        return DetailDataEntity(
            id = detailData.id,
            name = detailData.name,
            summary = detailData.summary,
            description = detailData.description,
            imageLogo = detailData.imageLogo,
            category = detailData.category,
            cityName = detailData.cityName,
            mediaCover = detailData.mediaCover,
            beginTime = detailData.beginTime,
            registrants = detailData.registrants,
            endTime = detailData.endTime,
            link = detailData.link
        )
    }
}