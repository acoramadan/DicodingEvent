package com.muflidevs.dicodingevent.data.database.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.muflidevs.dicodingevent.data.database.local.entity.DetailDataEntity
import com.muflidevs.dicodingevent.data.database.local.room.EventDAO
import com.muflidevs.dicodingevent.data.database.local.room.EventRoomDatabase
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepository(application: Application) {
    private val mEventDao : EventDAO
    init {
        val db = EventRoomDatabase.getDatabase(application)
        mEventDao = db.eventDAO()
    }

    fun getAllEvent(): LiveData<List<DetailDataEntity>> = mEventDao.getAllEvents()

    suspend fun insert(detailData: DetailDataEntity) {
        withContext(Dispatchers.IO) {
            mEventDao.insert(detailData)
        }
    }

    suspend fun delete(detailData: DetailDataEntity) {
        withContext(Dispatchers.IO) {
            mEventDao.delete(detailData)
        }
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