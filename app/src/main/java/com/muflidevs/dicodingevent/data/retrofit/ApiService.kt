package com.muflidevs.dicodingevent.data.retrofit

import com.muflidevs.dicodingevent.data.response.EventsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    fun getEvents(
        @Query("active") active: Int = 1,
        @Query("limit") limit : Int = 40,
        @Query("q") query : String? = null
    ): Call<EventsResponse>

    @GET("events")
    fun getEventsDetails(@Query("id") eventId : Int) : Call<EventsResponse>
}