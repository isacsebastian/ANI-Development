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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tesis1.components.CustomCardTopics
import com.example.tesis1.components.NavBar
import com.example.tesis1.components.SearchBar
import com.example.tesis1.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoomTopics(navController: NavHostController, roomTitle: String, topicTitles: List<String>) {
    val isSearchVisible = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var selectedRoom by remember { mutableStateOf<String?>(null) }
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
                    text = roomTitle,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(start = 8.dp),
                    color = surfaceContainerDark
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = surfaceContainerDark,
                    modifier = Modifier.clickable { isSearchVisible.value = !isSearchVisible.value
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
                SearchBar(
                    searchText = searchText,
                    onSearchTextChanged = { text -> searchText = text },
                    roomTitles = topicTitles,
                    topicTitles = emptyList(),

                    onSearchRoomSelected = { room ->
                        selectedRoom = room
                    },
                    onSearchTopicSelected = { topic ->
                        selectedRoom = topic
                    }
                )
            }

            Column(Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
            ) {
                topicTitles.forEach { topicTitle ->
                    CustomCardTopics(
                        navController,
                        topicTitle = topicTitle,
                        topicSubtitle = roomTitle,
                        searchText = searchText
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            NavBar(currentScreen = "Rooms", navController = navController)
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun RoomTopicsPreview() {
    RoomTopics(navController = NavHostController(LocalContext.current), roomTitle = "Marketing 1", topicTitles = listOf("Estrategias Marketing",
        "Diseño de Interfaces", "Desarrollo de Software", "Cuidado del Ambiente", "Cuidado Canino"))
}
