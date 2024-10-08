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

class MainViewModel : ViewModel() {

    private val _listEvents = MutableLiveData<List<DetailData>>()
    val listEvent : LiveData<List<DetailData>> = _listEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
         const val TAG = "MainViewModel"
    }
    init{
        findEventHorizontal()

    }
    private fun findEventHorizontal() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getEvents(active = 1)
        client.enqueue(object : Callback<EventsResponse> {
            override fun onResponse(
                call : Call<EventsResponse>,
                response: Response<EventsResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful) {
                    _listEvents.value = response.body()?.data
                    Log.e(TAG,"$response\n${response.body()?.data}")
                }else {
                    Log.e(TAG,"onFailure : ${response.message()}")
                }
            }

            override fun onFailure(p0: Call<EventsResponse>, p1: Throwable) {
                _isLoading.value = false
                Log.e(TAG,"onFailure : ${p1.message}")
            }
        })
    }
}