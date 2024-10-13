package com.muflidevs.dicodingevent.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muflidevs.dicodingevent.data.response.DetailData

@Dao
interface EventDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(detail : DetailData)

    @Delete
    fun delete(detail : DetailData)

    @Query("SELECT * FROM detaildata")
    fun getAllEvents() : LiveData<List<DetailData>>
}