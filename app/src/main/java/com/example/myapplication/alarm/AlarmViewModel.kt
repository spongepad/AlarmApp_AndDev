package com.example.myapplication.alarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = AlarmRepository(application)
    private val alarms = repo.getAlarms()

    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repo.insert(alarm)
        }
    }

    fun getAlarms(): LiveData<List<Alarm>> {
        return this.alarms
    }
}
