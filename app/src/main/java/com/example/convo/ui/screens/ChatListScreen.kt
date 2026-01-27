package com.example.convo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.convo.ui.components.ChatListItem
import com.example.convo.ui.components.StoryItem

@Composable
fun ChatListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary) // Background for the top half
    ) {
        // --- Top Header Section (Purple Area) ---
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chats",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Stories / Status Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { AddStoryItem() } // The "+" Button
                items(5) { // 5 Mock users
                    StoryItem(name = "User $it")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // --- Main Content Section (White Sheet) ---
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp), // The Curved Top
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(24.dp)) {

                // Search Bar
                SearchBarPlaceholder()

                Spacer(modifier = Modifier.height(16.dp))

                // The Chat List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(10) { index ->
                        ChatListItem(
                            name = "Prateek Saini $index",
                            message = "Of course, what can I help you...",
                            time = "2 min ago",
                            unreadCount = if (index < 3) 3 else 0 // First 3 have badges
                        )
                    }
                }
            }
        }
    }
}

