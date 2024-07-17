package com.example.tesis1.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VoiceChat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tesis1.ui.theme.*

@Composable
fun NavBar(
    currentScreen: String,
    navController: NavController
) {
    Surface(color = outlineVariantLight) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 64.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavBarItem(
                title = "Rooms",
                icon = Icons.Filled.MeetingRoom,
                isSelected = currentScreen == "Rooms",
                onClick = { navController.navigate("room") }
            )
            NavBarItem(
                title = "History",
                icon = Icons.Filled.VoiceChat,
                isSelected = currentScreen == "History",
                onClick = { navController.navigate("history") }
            )
            NavBarItem(
                title = "Settings",
                icon = Icons.Filled.Settings,
                isSelected = currentScreen == "Settings",
                onClick = { navController.navigate("settings") }
            )
        }
    }
}

@Composable
fun NavBarItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = onSecondaryContainerLight,
            modifier = Modifier
                .padding(bottom = 3.dp)
                .size(width = 56.dp, height = 32.dp)
                .background(if (isSelected) secondaryContainerLight else Color.Transparent, shape = RoundedCornerShape(16.dp)))
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = onSecondaryContainerLight
        )
    }
}

@Preview
@Composable
fun NavBarPreview() {
    NavBar(currentScreen = "Rooms", navController = NavHostController(LocalContext.current))
}
