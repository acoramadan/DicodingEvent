package com.muflidevs.dicodingevent.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import com.muflidevs.dicodingevent.data.remote.response.EventsResponse
import com.muflidevs.dicodingevent.data.remote.retrofit.ApiConfig
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModel.Companion
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModelFinish : ViewModel() {

    private val _listEvents = MutableLiveData<List<DetailData>>()
    val listEvent: LiveData<List<DetailData>> = _listEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        findEventVertical()
    }

    fun searchFinishedEvents(active: Int, limit: Int, query: String?) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiConfig.getApiService()
                    .getEvents(active = active, limit = limit, query = query)
                _listEvents.value = response.data

            } catch (e: Exception) {
                Log.e(MainViewModel.TAG, "onFailure : ${e.message}")
            } finally {
                _isLoading.value = false
            }

        }

    }

    private fun findEventVertical() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiConfig.getApiService().getEvents(0)

                _listEvents.value = response.data

            } catch (e: Exception) {
                Log.e(MainViewModel.TAG, "onFailure : ${e.message}")
            } finally {
                _isLoading.value = false
            }

        }

    }
}