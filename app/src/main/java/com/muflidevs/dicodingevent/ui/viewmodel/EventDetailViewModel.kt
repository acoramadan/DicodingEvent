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

class EventDetailViewModel : ViewModel() {
    private val _eventDetail = MutableLiveData<List<DetailData>>()
    val eventDetail : LiveData<List<DetailData>> get() = _eventDetail


    fun eventDetails(eventId : Int) {
                ApiConfig.getApiService().getEventsDetails(eventId).enqueue(object : Callback<EventsResponse> {
                    override fun onResponse(call: Call<EventsResponse>, response: Response<EventsResponse>) {
                        if(response.isSuccessful) {
                            Log.d("EventDetails", "Response: ${response.body()}")
                            _eventDetail.value = response.body()?.data
                        }
                    }
                    override fun onFailure(call: Call<EventsResponse>, msg: Throwable) {
                       Log.e("EventDetails : ", "onFail : ${msg.message}")
                    }
                })
            }
}