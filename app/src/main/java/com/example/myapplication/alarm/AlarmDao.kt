package com.example.myapplication.alarm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Insert
    fun insertAlarm(alarm: Alarm)

    @Query("SELECT * FROM Alarm")
    fun getAllAlarms(): LiveData<List<Alarm>>

    @Query("SELECT * from alarm WHERE id = :id")
    fun getAlarm(id: Int): Flow<Alarm>
}
