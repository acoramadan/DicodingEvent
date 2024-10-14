package com.muflidevs.dicodingevent.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import com.muflidevs.dicodingevent.data.remote.response.EventsResponse
import com.muflidevs.dicodingevent.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listEvents = MutableLiveData<List<DetailData>>()
    val listEvent: LiveData<List<DetailData>> = _listEvents

    private val _listEventsVertical = MutableLiveData<List<DetailData>>()
    val listEventVertical: LiveData<List<DetailData>> = _listEventsVertical

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        findEventHorizontal()
        findEventVertical()
    }

    private fun findEventHorizontal() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiConfig.getApiService().getEvents(1, 5)

                _listEvents.value = response.data

            } catch (e: Exception) {
                Log.e(TAG, "onFailure : ${e.message}")
            } finally {
                _isLoading.value = false
            }

        }

    }

    private fun findEventVertical() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _isLoading.value = true
                val response = ApiConfig.getApiService().getEvents(0, 5)

                _listEventsVertical.value = response.data

            } catch (e: Exception) {
                Log.e(TAG, "onFailure : ${e.message}")
            } finally {
                _isLoading.value = false
            }

        }

    }

}