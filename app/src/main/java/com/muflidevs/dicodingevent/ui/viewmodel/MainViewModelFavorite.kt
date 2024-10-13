package com.muflidevs.dicodingevent.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.muflidevs.dicodingevent.data.database.repository.EventRepository
import com.muflidevs.dicodingevent.data.response.DetailData

class MainViewModelFavorite(application: Application) : ViewModel() {
    private val mEventRepository: EventRepository = EventRepository(application)

    fun insert(detailData: DetailData) {
        mEventRepository.insert(detailData)
    }

    fun delete(detailData: DetailData) {
        mEventRepository.delete(detailData)
    }

    fun getAllEvents(): LiveData<List<DetailData>> = mEventRepository.getAllEvent()
}
