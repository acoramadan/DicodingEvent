package com.muflidevs.dicodingevent.data.database.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muflidevs.dicodingevent.data.database.local.entity.DetailDataEntity

@Dao
interface EventDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(detail: DetailDataEntity)

    @Delete
    suspend fun delete(detail: DetailDataEntity)

    @Query("SELECT * FROM detaildata")
    fun getAllEvents(): LiveData<List<DetailDataEntity>>
}