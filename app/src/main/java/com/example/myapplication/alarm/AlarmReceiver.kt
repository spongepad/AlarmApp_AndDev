package com.example.myapplication.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(context, NotificationManager::class.java) as NotificationManager
        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
            .setContentTitle("알람")
            .setContentText("알람이 울립니다!")
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val NOTIFICATION_ID = 1
    }
}