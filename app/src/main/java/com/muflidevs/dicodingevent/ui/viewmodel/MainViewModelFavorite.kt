package com.muflidevs.dicodingevent.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muflidevs.dicodingevent.data.database.local.entity.DetailDataEntity
import com.muflidevs.dicodingevent.data.database.local.repository.EventRepository
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import kotlinx.coroutines.launch

class MainViewModelFavorite(application : Application) : ViewModel() {

    private val mEventRepository = EventRepository(application)

    fun insert(detailData: DetailData) {
        viewModelScope.launch {
            val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
            mEventRepository.insert(detailDataEntity)
        }
    }
    fun insert(detailData: DetailDataEntity) {
        viewModelScope.launch {
            val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
            mEventRepository.insert(detailDataEntity)
        }
    }

    fun delete(detailData: DetailData) {
        viewModelScope.launch {
            val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
            mEventRepository.delete(detailDataEntity)
        }
    }
    fun delete(detailData: DetailDataEntity) {
        viewModelScope.launch {
            val detailDataEntity = mEventRepository.mapRemoteToLocal(detailData)
            mEventRepository.delete(detailDataEntity)
        }
    }
    fun getAllEvents(): LiveData<List<DetailDataEntity>> = mEventRepository.getAllEvent()
}
