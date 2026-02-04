package com.example.convo.ui.chatdetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.convo.domain.model.MessageType
import com.example.convo.ui.chatdetails.MessageBubbleUiState
import com.example.convo.ui.theme.AppTheme

// --- Component: Message Bubble ---
@Composable
fun MessageBubble(message: MessageBubbleUiState) {

    // Dynamic Shape: Pointed corner on the sender's side
    val bubbleShape = if (message.isMe) {
        RoundedCornerShape(topStart = 20.dp, topEnd = 0.dp, bottomStart = 20.dp, bottomEnd = 20.dp)
    } else {
        RoundedCornerShape(topStart = 0.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp)
    }

    val bgColor = if (message.isMe) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
    val textColor = if (message.isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
    val timeColor =  MaterialTheme.colorScheme.onTertiaryContainer

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.isMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            color = bgColor,
            shape = bubbleShape,
            modifier = Modifier.widthIn(max = 280.dp) // Don't stretch full width
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = message.content,
                    color = textColor,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                message.timeStamp?.let {
                    Text(
                        text = it,
                        color = timeColor,
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageBubble() {
    AppTheme() {
        MessageBubble(
            message = MessageBubbleUiState(
                content = "this is a sample message",
                type = MessageType.CHAT,
                isMe = true
            )
        )
    }
}