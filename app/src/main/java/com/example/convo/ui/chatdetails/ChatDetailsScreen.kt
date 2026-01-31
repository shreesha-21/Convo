package com.example.convo.ui.chatdetails

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.convo.domain.model.Message
import com.example.convo.domain.model.MessageType
import com.example.convo.core.ui.chatdetails.components.ChatInputBar
import com.example.convo.core.ui.chatdetails.components.ChatTopBar
import com.example.convo.core.ui.chatdetails.components.MessageBubble
import com.example.convo.core.ui.theme.AppTheme

@Composable
fun ChatDetailScreen(
    onBackClick: () -> Unit
) {
    // Mock Messages
    val messages = remember {
        listOf(
            Message(sender = "me", content = "Hi Shashi, good morning!! \uD83D\uDC4B", type =  MessageType.CHAT, timestamp =  "06:02", ),
            Message(sender = "other", content = "Halo! Good Morning, whats up man?", type = MessageType.CHAT, timestamp = "06:12",),
            Message(sender = "me", content = "Sorry to bother. Can i ask you for a help today?", type =  MessageType.CHAT, timestamp =  "06:30", ),
            Message(sender = "other", content = "Of course, what can i help you with??", type = MessageType.CHAT, timestamp =  "06:45",)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary) // Background for Top Bar area
    ) {

        // 1. Custom Top Bar (Purple)
        ChatTopBar(
            username = "Shashi Kumar",
            status = "Online",
            onBackClick = onBackClick
        )

        // 2. Chat Area (White Sheet)
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp) // Space for the curved top
            ) {
                // Message List
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Date Header
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Surface(
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "Today",
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                        }
                    }

                    items(messages) { msg ->
                        MessageBubble(message = msg)
                    }
                }

                // Input Bar
                ChatInputBar()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChatDetailsScreen() {
    AppTheme() {
        ChatDetailScreen(onBackClick = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrevieChatDetailsScreenDark() {
    AppTheme() {
        ChatDetailScreen(onBackClick = {})
    }
}