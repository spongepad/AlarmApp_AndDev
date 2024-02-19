package com.example.myapplication.alarm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AlarmRepository(application: Application) : ViewModel() {
    private val alarmDB = AppDatabase.getInstance(application)!!
    private val alarmDao: AlarmDao = alarmDB.alarmDao()
    private val alarms: LiveData<List<Alarm>> = alarmDao.getAllAlarms()

    fun getAlarms(): LiveData<List<Alarm>> {
        return alarms
    }

    fun insert(alarm: Alarm) {
        viewModelScope.launch {
            alarmDao.insertAlarm(alarm)
        }
    }

}