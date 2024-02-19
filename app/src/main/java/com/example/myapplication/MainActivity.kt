package com.example.myapplication

// MainActivity.kt
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.alarm.AlarmViewModel
import com.example.myapplication.alarm.AlarmViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var alarmViewModel: AlarmViewModel
    private lateinit var alarmViewModelFactory: AlarmViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmViewModelFactory = AlarmViewModelFactory(application)
        alarmViewModel = ViewModelProvider(this, alarmViewModelFactory).get(AlarmViewModel::class.java)
        createNotificationChannel()
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                AlarmApp(applicationContext, alarmViewModel)
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "alarm_channel"
    }
}


@Composable
fun AlarmApp(context: Context, alarmViewModel: AlarmViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "alarmList") {
        composable("alarmList") { AlarmListScreen(navController, alarmViewModel) }
        composable("alarmScreen") { AlarmScreen(context, navController, alarmViewModel) }
    }
}