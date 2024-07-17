package com.example.tesis1.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tesis1.components.CustomCard
import com.example.tesis1.components.CustomCardHistory
import com.example.tesis1.components.NavBar
import com.example.tesis1.components.SearchBarHistory
import com.example.tesis1.ui.theme.surfaceDimLight

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(navController: NavHostController, historyTopicTitles: List<String>) {
    var searchText by remember { mutableStateOf("") }
    var selectedHistory by remember { mutableStateOf<String?>(null) }

    val historyTitles = listOf(
        "Marketing 1",
        "Marketing 2",
        "Marketing 3",
        "Marketing 4",
        "Marketing 5",
    )

    Surface(color = surfaceDimLight) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBarHistory(
                searchText = searchText,
                onSearchTextChanged = { text ->
                    searchText = text
                    selectedHistory = null
                },
                historyTitles = historyTitles,
                historyTopicTitles = historyTopicTitles,
                onSearchHistorySelected = { history ->
                    selectedHistory = history
                },
                onSearchHistoryTopicSelected = {}
            )

            Spacer(modifier = Modifier.height(1.dp))

            val filteredHistoryTitles = if (selectedHistory != null) {
                listOf(selectedHistory!!)
            } else {
                historyTitles.filter {
                    it.contains(searchText, ignoreCase = true)
                }
            }

            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                filteredHistoryTitles.forEach { historyTitle ->
                    CustomCardHistory(
                        navController = navController,
                        historyTitle = historyTitle,
                        historySubtitle = historyTopicTitles.firstOrNull() ?: "",
                        searchText = searchText
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            NavBar(currentScreen = "History", navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(
        navController = rememberNavController(),
        historyTopicTitles = listOf(
            "Estrategias Marketing",
            "Diseño de Interfaces",
            "Desarrollo de Software",
            "Cuidado del Ambiente",
            "Cuidado Canino"
        )
    )
}
