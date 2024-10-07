package com.muflidevs.dicodingevent.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.data.response.EventsResponse
import com.muflidevs.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModelFinish : ViewModel() {
    private val _Events = MutableLiveData<DetailData>()
    val event : LiveData<DetailData> = _Events

    private val _listEvents = MutableLiveData<List<DetailData>>()
    val listEvent : LiveData<List<DetailData>> = _listEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "MainViewModel"
    }
    init{
        findEventVertical()

    }

    private fun findEventVertical() {
        val client = ApiConfig.getApiService().getEvents(active = 0)
        client.enqueue(object : Callback<EventsResponse> {
            override fun onResponse(
                call: Call<EventsResponse>,
                response : Response<EventsResponse>
            ) {
                if(response.isSuccessful) {
                    _listEvents.value = response.body()?.data
                }
                    else{
                        Log.e(TAG, "onFailure : ${response.message()}")
                    }
                }
            override fun onFailure(error : Call<EventsResponse>, msg : Throwable) {
                Log.e(TAG, "onFailure : ${msg.message}")
            }
        })
    }
}