package com.kkm.geodetic.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryTable::class], version = 1, exportSchema = false)
abstract class HistoryDatabase :  RoomDatabase(){

    abstract fun destRoomDao() : HistoryDao

    companion object {
        private var instance: HistoryDatabase? = null

        @Synchronized
        fun getInstance(context: Context): HistoryDatabase? {
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDatabase::class.java,
                    "database-dest"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}