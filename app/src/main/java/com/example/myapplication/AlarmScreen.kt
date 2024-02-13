package com.example.myapplication

// AlarmScreen.kt
import android.content.Context
import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.myapplication.alarm.Alarm
import com.example.myapplication.alarm.AlarmViewModel
import com.example.myapplication.alarm.setAlarm

@Composable
fun AlarmScreen(applicationContext: Context, navController: NavController, alarmViewModel: AlarmViewModel) {
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "알람 시간 설정", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        AndroidView(
            factory = { context ->
                TimePicker(context).apply {
                    setIs24HourView(true)
                    setOnTimeChangedListener { _, hour, minute ->
                        selectedHour = hour
                        selectedMinute = minute
                    }
                }
            },
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val alarmId = (alarmViewModel.alarms.value.maxByOrNull { it.id }?.id ?: 0) + 1
            var alarm = Alarm(0, selectedHour, selectedMinute)

            alarmViewModel.addAlarm(alarm)
            setAlarm(applicationContext, alarm)
            navController.popBackStack()
        }) {
            Text("알람 등록")
        }
    }
}
