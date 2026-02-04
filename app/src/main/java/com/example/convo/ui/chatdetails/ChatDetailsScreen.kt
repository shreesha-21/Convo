package com.example.convo.ui.chatdetails

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.convo.ui.chatdetails.components.ChatInputBar
import com.example.convo.ui.chatdetails.components.ChatTopBar
import com.example.convo.ui.chatdetails.components.DayBubble
import com.example.convo.ui.chatdetails.components.MessageBubble
import com.example.convo.ui.theme.AppTheme

@Composable
fun ChatDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = viewModel()
) {
    //  this contains list of all the messages sent and received
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    // this is the text currently in the chat input bar
    val currentText by viewModel.messageText.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary) // Background for Top Bar area
    ) {

        //  Custom Top Bar (Purple)
        ChatTopBar(
            username = "username",
            status = "Online",
            onBackClick = onBackClick
        )

        // Chat Area (White Sheet)
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
                    // TODO: Implement date header
                    item {
                        DayBubble()
                    }

                    items(messages) { msg ->
                        MessageBubble(message = msg)
                    }
                }

                // Input Bar
                ChatInputBar(
                    currentText = currentText,
                    onMessageChange = {viewModel.onMessageChange(it)},
                    onSend = {viewModel.sendMessage()}
                )
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