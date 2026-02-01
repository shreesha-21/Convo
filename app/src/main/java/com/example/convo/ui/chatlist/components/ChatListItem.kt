package com.example.convo.ui.chatlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.convo.domain.model.ChatSummary
import com.example.convo.ui.theme.AppTheme

// --- Component: Chat List Item ---
@Composable
fun ChatListItem(
    chatSummary: ChatSummary,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Surface(
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            color = Color.LightGray
        ) {
            // Place your Image composable here
            Icon(
                Icons.Default.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Name and Message
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable(enabled = true, onClick = onClick)
        ) {
            Text(
                text = chatSummary.sender,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            // TODO: Implement this last message sent text
            //  Shows the last sent message
//            Text(
//                text = chatSummary,
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Gray,
//                maxLines = 1
//            )
        }

        // Time and Badge
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = chatSummary.timeStamp,
                style = MaterialTheme.typography.labelMedium,
                color = if (chatSummary.unreadCount > 0) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
            )

            if (chatSummary.unreadCount > 0) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(22.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = chatSummary.unreadCount.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatListItem() {
    AppTheme() {
        ChatListItem(
            ChatSummary(
                sender = "name",
                timeStamp = "12:0",
                unreadCount = 3
            ),
            onClick = {}
        )
    }
}