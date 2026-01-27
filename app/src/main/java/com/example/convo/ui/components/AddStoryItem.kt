package com.example.convo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//  Composable used to add a new story
@Composable
fun AddStoryItem() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.2f)
            ) {
                // Placeholder for user image
            }
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Your Story", color = Color.White, fontSize = 12.sp)
    }
}