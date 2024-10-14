package com.muflidevs.dicodingevent.data.database.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "detaildata")
@Parcelize
data class DetailDataEntity(
    @PrimaryKey
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "summary")
    var summary: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "image_logo")
    var imageLogo: String? = null,

    @ColumnInfo(name = "media_cover")
    var mediaCover: String? = null,

    @ColumnInfo(name = "category")
    var category: String? = null,

    @ColumnInfo(name = "owner_name")
    var ownerName: String? = null,

    @ColumnInfo(name = "city_name")
    var cityName: String? = null,

    @ColumnInfo(name = "quota")
    var quota: Int = 0,

    @ColumnInfo(name = "registrants")
    var registrants: Int = 0,

    @ColumnInfo(name = "begin_time")
    var beginTime: String? = null,

    @ColumnInfo(name = "end_time")
    var endTime: String? = null,

    @ColumnInfo(name = "link")
    var link: String? = null
) : Parcelable
