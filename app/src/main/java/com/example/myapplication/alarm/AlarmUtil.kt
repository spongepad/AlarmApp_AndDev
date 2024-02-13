package com.example.myapplication.alarm

// AlarmUtil.kt
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

fun setAlarm(context: Context, alarm: Alarm) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, alarm.id, intent,
        PendingIntent.FLAG_IMMUTABLE)

    val calendar: Calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, alarm.hour)
        set(Calendar.MINUTE, alarm.minute)
        set(Calendar.SECOND, 0)
    }

    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
}

fun cancelAlarm(context: Context, alarmId: Int) {
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, PendingIntent.FLAG_IMMUTABLE)
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent)
}