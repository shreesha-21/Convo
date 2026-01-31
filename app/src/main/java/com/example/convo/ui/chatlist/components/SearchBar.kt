package com.example.convo.ui.chatlist.`c`

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.convo.core.ui.theme.AppTheme

// --- Component: Search Bar ---
@Composable
fun SearchBarPlaceholder(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(24.dp), // Pill shape
        color = MaterialTheme.colorScheme.secondary, // Light gray background
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search", color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.Menu, contentDescription = "Filter", tint = Color.Gray)
        }
    }
}

@Preview
@Composable
fun PreviewSearchBarPlaceholder() {
    AppTheme() {
        SearchBarPlaceholder(
            onSearch = {}
        )
    }
}