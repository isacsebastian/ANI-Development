package com.example.tesis1.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tesis1.ui.theme.surfaceContainerDark
import com.example.tesis1.ui.theme.surfaceDimLight
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCard(
    navController: NavController,
    roomTitle: String,
    roomSubtitle: String,
    searchText: String,
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            delay(1000)
        }
    }
    if (roomTitle.contains(searchText, ignoreCase = true) || searchText.isBlank()) {
        Surface(
            color = surfaceDimLight,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(4.dp)
                .clickable { navController.navigate("topics/$roomTitle") }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = surfaceContainerDark,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = roomTitle,
                        style = MaterialTheme.typography.headlineMedium,
                        color = surfaceContainerDark,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = roomSubtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = surfaceContainerDark
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm a")),
                    style = MaterialTheme.typography.labelSmall,
                    color = surfaceContainerDark
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCardTopics(
    navController: NavController,
    topicTitle: String,
    topicSubtitle: String,
    searchText: String
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            delay(1000)
        }
    }
    if (topicTitle.contains(searchText, ignoreCase = true) || searchText.isBlank()) {
        Surface(
            color = surfaceDimLight,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(4.dp)
                .clickable { navController.navigate("meeting/$topicSubtitle/$topicTitle") }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = surfaceContainerDark,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = topicTitle,
                        style = MaterialTheme.typography.headlineMedium,
                        color = surfaceContainerDark,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = topicSubtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = surfaceContainerDark
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm a")),
                    style = MaterialTheme.typography.labelSmall,
                    color = surfaceContainerDark
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CustomCardTopicsPreview() {
    CustomCard(
        navController = NavController(LocalContext.current),
        roomTitle = "Sample Room Title",
        roomSubtitle = "Sample Room Subtitle",
        searchText = ""
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCardHistory(
    navController: NavController,
    historyTitle: String,
    historySubtitle: String,
    searchText: String,
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            delay(1000)
        }
    }
    if (historyTitle.contains(searchText, ignoreCase = true) || searchText.isBlank()) {
        Surface(
            color = surfaceDimLight,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(4.dp)
                .clickable { navController.navigate("historyTopics/$historyTitle") }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = surfaceContainerDark,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = historyTitle,
                        style = MaterialTheme.typography.headlineMedium,
                        color = surfaceContainerDark
                    )

                    Text(
                        text = historySubtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = surfaceContainerDark
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm a")),
                    style = MaterialTheme.typography.labelSmall,
                    color = surfaceContainerDark
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCardTopicsHistory(
    navController: NavController,
    historyTopicTitle: String,
    historyTopicSubtitle: String,
    searchText: String
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            delay(1000)
        }
    }
    if (historyTopicTitle.contains(searchText, ignoreCase = true) || searchText.isBlank()) {
        Surface(
            color = surfaceDimLight,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(4.dp)
                .clickable { navController.navigate("records/$historyTopicTitle") }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = surfaceContainerDark,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = historyTopicTitle,
                        style = MaterialTheme.typography.headlineSmall,
                        color = surfaceContainerDark
                    )

                    Text(
                        text = historyTopicSubtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = surfaceContainerDark
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm a")),
                    style = MaterialTheme.typography.labelSmall,
                    color = surfaceContainerDark
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCardRecords(
    navController: NavController,
    recordTitle: String,
    historyTitle: String,
    searchText: String
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            delay(1000)
        }
    }
    if (recordTitle.contains(searchText, ignoreCase = true) || searchText.isBlank()) {
        Surface(
            color = surfaceDimLight,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(4.dp)
                .clickable { navController.navigate("recordDetail/$historyTitle/$recordTitle") }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = surfaceContainerDark,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = recordTitle,
                        style = MaterialTheme.typography.headlineSmall,
                        color = surfaceContainerDark
                    )

                    Text(
                        text = historyTitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = surfaceContainerDark
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm a")),
                    style = MaterialTheme.typography.labelSmall,
                    color = surfaceContainerDark
                )
            }
        }
    }
}

