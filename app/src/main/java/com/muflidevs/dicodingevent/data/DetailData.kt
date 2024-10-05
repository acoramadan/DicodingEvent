package com.muflidevs.dicodingevent.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Time

@Parcelize
data class DetailData(
    val name : String,
    val ownerName : String,
    val beginTime : String,
    val registrant : String,
    val description : String,
    val image : String
) : Parcelable