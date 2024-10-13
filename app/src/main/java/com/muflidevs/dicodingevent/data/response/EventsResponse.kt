package com.muflidevs.dicodingevent.data.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class EventsResponse (
    @field:SerializedName("error")
    val errorMsg : String,

    @field:SerializedName("message")
    val message : String,

    @field:SerializedName("listEvents")
    val data : List<DetailData>
)
@Entity
@Parcelize
data class DetailData(
    @PrimaryKey
    @field:SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "name")
    @field:SerializedName("name")
    var name: String? = null,

    @ColumnInfo(name = "summary")
    @field:SerializedName("summary")
    var summary: String? = null,

    @ColumnInfo(name = "description")
    @field:SerializedName("description")
    var description: String? = null,

    @ColumnInfo(name = "image_logo")
    @field:SerializedName("imageLogo")
    var imageLogo: String? = null,

    @ColumnInfo(name = "media_cover")
    @field:SerializedName("mediaCover")
    var mediaCover: String? = null,

    @ColumnInfo(name = "category")
    @field:SerializedName("category")
    var category: String? = null,

    @ColumnInfo(name = "owner_name")
    @field:SerializedName("ownerName")
    var ownerName: String? = null,

    @ColumnInfo(name = "city_name")
    @field:SerializedName("cityName")
    var cityName: String? = null,

    @ColumnInfo(name = "quota")
    @field:SerializedName("quota")
    var quota: Int = 0,

    @ColumnInfo(name = "registrants")
    @field:SerializedName("registrants")
    var registrants: Int = 0,

    @ColumnInfo(name = "begin_time")
    @field:SerializedName("beginTime")
    var beginTime: String? = null,

    @ColumnInfo(name = "end_time")
    @field:SerializedName("endTime")
    var endTime: String? = null,

    @ColumnInfo(name = "link")
    @field:SerializedName("link")
    var link: String? = null
) : Parcelable

