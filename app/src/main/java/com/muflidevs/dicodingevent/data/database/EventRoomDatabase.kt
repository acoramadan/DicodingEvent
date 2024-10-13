package com.muflidevs.dicodingevent.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muflidevs.dicodingevent.data.response.DetailData

@Database(entities = [DetailData::class], version = 1)
abstract class EventRoomDatabase : RoomDatabase() {
    abstract fun eventDAO() : EventDAO

    companion object {
        @Volatile
        private var INSTANCE : EventRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context : Context) : EventRoomDatabase {
            if (INSTANCE == null) {
               INSTANCE = Room.databaseBuilder(context.applicationContext,
               EventRoomDatabase::class.java, "event_database").build()
            }
            return INSTANCE as EventRoomDatabase
        }
    }
}