package com.example.tesis1.appnavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tesis1.screens.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val topicTitles = listOf("Estrategias Marketing", "Diseño de Interfaces", "Desarrollo de Software", "Cuidado del Ambiente", "Cuidado Canino")
    val historyTopicTitles = listOf("Estrategias Marketing", "Diseño de Interfaces", "Desarrollo de Software", "Cuidado del Ambiente")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = "login",
                modifier = Modifier.weight(1f)
            ) {
                composable("login") { LoginScreen(navController) }
                composable("room") { RoomScreen(navController, topicTitles = topicTitles) }
                composable(
                    "topics/{roomTitle}",
                    arguments = listOf(navArgument("roomTitle") { type = NavType.StringType })
                ) { backStackEntry ->
                    val roomTitle = backStackEntry.arguments?.getString("roomTitle") ?: ""
                    RoomTopics(navController, roomTitle, topicTitles)
                }
                composable(
                    "meeting/{roomTitle}/{topicTitle}",
                    arguments = listOf(
                        navArgument("roomTitle") { type = NavType.StringType },
                        navArgument("topicTitle") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val roomTitle = backStackEntry.arguments?.getString("roomTitle") ?: ""
                    val topicTitle = backStackEntry.arguments?.getString("topicTitle") ?: ""
                    MeetingScreen(navController, meetingTitle = roomTitle, meetingDescription = topicTitle)
                }
                composable("history") { HistoryScreen(navController, historyTopicTitles = historyTopicTitles) }
                composable(
                    "historyTopics/{historyTitle}",
                    arguments = listOf(navArgument("historyTitle") { type = NavType.StringType })
                ) { backStackEntry ->
                    val historyTitle = backStackEntry.arguments?.getString("historyTitle") ?: ""
                    TopicsScreen(navController, historyTitle, historyTopicTitles)
                }
                composable(
                    "records/{topicTitle}",
                    arguments = listOf(navArgument("topicTitle") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicTitle = backStackEntry.arguments?.getString("topicTitle") ?: ""
                    RecordsScreen(navController, topicTitle, listOf("Record 1", "Record 2", "Record 3", "Record 4"))
                }
                composable(
                    "recordDetail/{historyTitle}/{recordTitle}",
                    arguments = listOf(
                        navArgument("historyTitle") { type = NavType.StringType },
                        navArgument("recordTitle") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val historyTitle = backStackEntry.arguments?.getString("historyTitle") ?: ""
                    val recordTitle = backStackEntry.arguments?.getString("recordTitle") ?: ""
                    RecordScreen(navController, historyTitle, recordTitle)
                }
                composable("settings") { SettingsScreen(navController) }
            }
        }
    }
}
