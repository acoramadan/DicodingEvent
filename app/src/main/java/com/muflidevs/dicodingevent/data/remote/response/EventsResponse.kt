package com.muflidevs.dicodingevent.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class EventsResponse(
    @field:SerializedName("error")
    val errorMsg: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("listEvents")
    val data: List<DetailData>
)
@Parcelize
data class DetailData(
    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("summary")
    var summary: String? = null,

    @field:SerializedName("description")
    var description: String? = null,

    @field:SerializedName("imageLogo")
    var imageLogo: String? = null,

    @field:SerializedName("mediaCover")
    var mediaCover: String? = null,

    @field:SerializedName("category")
    var category: String? = null,

    @field:SerializedName("ownerName")
    var ownerName: String? = null,

    @field:SerializedName("cityName")
    var cityName: String? = null,

    @field:SerializedName("quota")
    var quota: Int = 0,

    @field:SerializedName("registrants")
    var registrants: Int = 0,

    @field:SerializedName("beginTime")
    var beginTime: String? = null,

    @field:SerializedName("endTime")
    var endTime: String? = null,

    @field:SerializedName("link")
    var link: String? = null
) : Parcelable
