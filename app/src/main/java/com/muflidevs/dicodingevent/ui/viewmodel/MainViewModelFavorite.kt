package com.muflidevs.dicodingevent.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.muflidevs.dicodingevent.data.database.local.entity.DetailDataEntity
import com.muflidevs.dicodingevent.data.database.local.repository.EventRepository
import com.muflidevs.dicodingevent.data.remote.response.DetailData

class MainViewModelFavorite(application: Application) : ViewModel() {
    private val mEventRepository: EventRepository = EventRepository(application)

    fun insert(detailData: DetailData) {
        val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
        mEventRepository.insert(detailDataEntity)
    }
    fun insert(detailData: DetailDataEntity) {
        val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
        mEventRepository.insert(detailDataEntity)
    }

    fun delete(detailData: DetailData) {
        val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
        mEventRepository.delete(detailDataEntity)
    }
    fun delete(detailData: DetailDataEntity) {
        val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
        mEventRepository.delete(detailDataEntity)
    }
    fun getAllEvents(): LiveData<List<DetailDataEntity>> = mEventRepository.getAllEvent()
}
