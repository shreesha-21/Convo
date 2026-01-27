package com.example.convo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.convo.ui.theme.AppTheme

// --- Component: Story Item ---
@Composable
fun StoryItem(name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier
                .size(60.dp)
                .padding(2.dp), // Gap for border
            shape = CircleShape,
            border = androidx.compose.foundation.BorderStroke(2.dp, Color.White), // White ring
            color = Color.LightGray
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, color = Color.White, fontSize = 12.sp)
    }
}

@Preview
@Composable
fun PreviewStoryItem() {
    AppTheme() {
        StoryItem(name = "name")
    }
}