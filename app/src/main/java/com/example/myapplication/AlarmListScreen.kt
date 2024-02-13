package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.alarm.Alarm
import com.example.myapplication.alarm.AlarmViewModel

@Composable
fun AlarmListScreen(navController: NavController, alarmViewModel: AlarmViewModel) {
    // 알람 데이터 목록. 실제 앱에서는 이 부분을 데이터 소스로부터 불러오거나 사용자 입력을 통해 추가됩니다.
    val alarms = alarmViewModel.alarms.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("alarmScreen")
            }) {
                Text("+")
            }
        },
        floatingActionButtonPosition = FabPosition.Center, // FAB를 중앙 하단에 위치시킵니다.
        // isFloatingActionButtonDocked 대신 사용할 수 있는 로직이나 디자인 요소를 여기에 추가
        bottomBar = {
            // 여기에 BottomAppBar를 사용할 경우 코드를 추가
            // 혹은 BottomAppBar 없이 FAB만 표시할 경우 이 부분을 비워둡니다.
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(alarms) { alarm ->
                AlarmItem(alarm)
            }
        }
    }
}

@Composable
fun AlarmItem(alarm: Alarm) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "알람 시간: ${alarm.hour}:${alarm.minute}", style = MaterialTheme.typography.bodyMedium)
            // 알람에 대한 추가 정보를 여기에 표시할 수 있습니다.
        }
    }
}