package com.example.tesis1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tesis1.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    roomTitles: List<String>,
    topicTitles: List<String>,
    onSearchRoomSelected: (String) -> Unit,
    onSearchTopicSelected: (String) -> Unit
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = searchText,
                shape = RoundedCornerShape(30.dp),
                onValueChange = { newValue ->
                    onSearchTextChanged(newValue)
                    isDropdownExpanded = newValue.isNotEmpty()
                },
                placeholder = { Text("Search in the Rooms", color = surfaceContainerDark) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = surfaceContainerDark ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = surfaceContainerDark,
                    unfocusedTextColor = surfaceContainerDark,
                    cursorColor = surfaceContainerDark,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(outlineVariantLight, shape = RoundedCornerShape(30.dp))
            )
            if (searchText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = surfaceContainerDark,
                    modifier = Modifier
                        .clickable {
                            onSearchTextChanged("")
                            isDropdownExpanded = false
                        }
                        .padding(end = 8.dp)
                )
            }
        }

        if (isDropdownExpanded && searchText.isNotEmpty()) {
            val filteredRoomTitles = roomTitles.filter {
                it.contains(searchText, ignoreCase = true)
            }

            if (filteredRoomTitles.isNotEmpty()) {
                Surface(
                    color = surfaceDimLight,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        filteredRoomTitles.forEach { roomTitle ->
                        Column {
                            Text(
                                text = roomTitle,
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                                    .clickable {
                                        onSearchRoomSelected(roomTitle)
                                        isDropdownExpanded = false
                                    },
                                color = surfaceContainerDark
                            )
                            topicTitles.forEach { topicTitle ->
                                Text(
                                    text = "    $topicTitle",
                                    modifier = Modifier
                                        .padding(vertical = 2.dp)
                                        .clickable {
                                            onSearchTopicSelected(topicTitle)
                                            isDropdownExpanded = false
                                        },
                                    color = surfaceContainerDark
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarHistory(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    historyTitles: List<String>,
    historyTopicTitles: List<String>,
    onSearchHistorySelected: (String) -> Unit,
    onSearchHistoryTopicSelected: (String) -> Unit
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = searchText,
                shape = RoundedCornerShape(16.dp),
                onValueChange = { newValue ->
                    onSearchTextChanged(newValue)
                    isDropdownExpanded = newValue.isNotEmpty()
                },
                placeholder = { Text("Search in the History", color = surfaceContainerDark) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = surfaceContainerDark ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = surfaceContainerDark,
                    unfocusedTextColor = surfaceContainerDark,
                    cursorColor = surfaceContainerDark,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(outlineVariantLight, shape = RoundedCornerShape(30.dp))
            )
            if (searchText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = surfaceContainerDark,
                    modifier = Modifier
                        .clickable {
                            onSearchTextChanged("")
                            isDropdownExpanded = false
                        }
                        .padding(end = 8.dp)
                )
            }
        }

        if (isDropdownExpanded && searchText.isNotEmpty()) {
            val filteredHistoryTitles = historyTitles.filter {
                it.contains(searchText, ignoreCase = true)
            }

            if (filteredHistoryTitles.isNotEmpty()) {
                Surface(
                    color = surfaceDimLight,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        filteredHistoryTitles.forEach { historyTopicTitle ->
                            Column {
                                Text(
                                    text = historyTopicTitle,
                                    modifier = Modifier
                                        .padding(vertical = 2.dp)
                                        .clickable {
                                            onSearchHistorySelected(historyTopicTitle)
                                            isDropdownExpanded = false
                                        },
                                    color = surfaceContainerDark
                                )
                                historyTopicTitles.forEach { historyTopicTitle ->
                                    Text(
                                        text = "    $historyTopicTitle",
                                        modifier = Modifier
                                            .padding(vertical = 2.dp)
                                            .clickable {
                                                onSearchHistoryTopicSelected(historyTopicTitle)
                                                isDropdownExpanded = false
                                            },
                                        color = surfaceContainerDark
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun SearchBarPreview() {
    val roomTitles = listOf("Software", "Marketing", "Design", "Nursing")
    var searchText by remember { mutableStateOf("") }

    SearchBar(
        searchText = searchText,
        onSearchTextChanged = { text -> searchText = text },
        roomTitles = roomTitles,
        onSearchRoomSelected = { },
        topicTitles = emptyList(),
        onSearchTopicSelected = {}
    )
}