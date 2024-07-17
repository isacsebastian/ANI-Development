package com.example.tesis1.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tesis1.components.CustomCardTopics
import com.example.tesis1.components.CustomCardTopicsHistory
import com.example.tesis1.components.NavBar
import com.example.tesis1.components.SearchBarHistory
import com.example.tesis1.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopicsScreen(navController: NavHostController, historyTitle: String, historyTopicTitles: List<String>) {
    val isSearchVisible = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var selectedHistory by remember { mutableStateOf<String?>(null) }
    Surface(
        color = surfaceDimLight,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = surfaceContainerDark,
                    modifier = Modifier.clickable { navController.navigateUp() }
                )
                Text(
                    text = historyTitle,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = 8.dp),
                    color = surfaceContainerDark
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = surfaceContainerDark,
                    modifier = Modifier.clickable {
                        isSearchVisible.value = !isSearchVisible.value
                        if (!isSearchVisible.value) {
                            searchText = ""
                        }
                    }
                )
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More",
                    tint = surfaceContainerDark,
                    modifier = Modifier.clickable { /* Handle three dots icon click */ }
                )
            }

            if (isSearchVisible.value) {
                SearchBarHistory(
                    searchText = searchText,
                    onSearchTextChanged = { text -> searchText = text },
                    historyTitles = historyTopicTitles,
                    historyTopicTitles = emptyList(),
                    onSearchHistorySelected = { history ->
                        selectedHistory = history
                    },
                    onSearchHistoryTopicSelected = { historyTopic ->
                        selectedHistory = historyTopic
                    }
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                historyTopicTitles.forEach { topicTitle ->
                    CustomCardTopicsHistory(
                        navController = navController,
                        historyTopicTitle = topicTitle,
                        historyTopicSubtitle = historyTitle,
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
fun TopicsScreenPreview() {
    TopicsScreen(
        navController = rememberNavController(),
        historyTitle = "Marketing 1",
        historyTopicTitles = listOf("Estrategias Marketing", "Neuromarketing", "Desarrollo de Estrategias", "Cuidado del producto", "Otros")
    )
}
