package com.muflidevs.dicodingevent.data.retrofit

import com.muflidevs.dicodingevent.data.response.EventsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("events/{id}")
    fun getEvents(@Path("id") id : Int) : Call<EventsResponse>
}