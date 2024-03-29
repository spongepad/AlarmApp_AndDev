package com.example.myapplication.alarm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Alarm::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

    companion object {
        private var instance: AppDatabase? =null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "alarm-database"
                    ).build()
                }
            }
            return instance
        }
    }
}