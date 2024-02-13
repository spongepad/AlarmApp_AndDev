package com.example.myapplication.alarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlarmViewModel : ViewModel() {
    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    val alarms = _alarms.asStateFlow()

    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch {
            _alarms.value = _alarms.value + alarm
        }
    }
}
