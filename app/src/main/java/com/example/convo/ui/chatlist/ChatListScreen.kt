package com.example.convo.ui.chatlist

import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.convo.R
import com.example.convo.ui.chatlist.c.AddStoryItem
import com.example.convo.ui.chatlist.c.ChatListItem
import com.example.convo.ui.chatlist.c.SearchBarPlaceholder
import com.example.convo.ui.chatlist.c.StoryItem
import com.example.convo.ui.chatlist.components.ChatSelectionEvent
import com.example.convo.ui.theme.AppTheme

@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit,
    viewModel: ChatListViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val chats by viewModel.chats.collectAsStateWithLifecycle()
    val stories by viewModel.stories.collectAsStateWithLifecycle()

    // Listens to the navigation intent sent from the view model and calls the navigation function
    LaunchedEffect(Unit) {
        viewModel.chatSelectionEvent.collect {
            when(it) {
                // calls the nav host function to navigate to the page
                is ChatSelectionEvent.NavigateToChatDetail -> onChatClick(it.username)
                // Logs error when failed to navigate
                is ChatSelectionEvent.FailedToNavigateToChatDetail -> Log.d("CHAT-LIST", it.errorMessage)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary) // Background for the top half
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Companion.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Stories / Status Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { AddStoryItem() }
                items(stories) { user ->// 5 Mock users
                    StoryItem(user)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // --- Main Content Section  ---
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp), // The Curved Top
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(modifier = Modifier.padding(24.dp)) {

                // Search Bar
                SearchBarPlaceholder(onSearch = {})

                Spacer(modifier = Modifier.height(16.dp))

                // The Chat List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(chats) { chat ->
                        ChatListItem(
                            chatSummary = chat,
                            onClick = {viewModel.onChatClick(chat.sender)}
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChatListScreen() {
    AppTheme() {
        ChatListScreen(onChatClick = {})
    }
}