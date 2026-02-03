package com.example.convo.data.repository

import com.example.convo.domain.model.ChatSummary
import com.example.convo.domain.model.Message
import com.example.convo.domain.model.MessageType
import com.example.convo.domain.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.collections.listOf

class MockChatRepository: ChatRepository {
    // Cache your chats here so they don't reload every time you switch screens
    private val _chats = MutableStateFlow<List<ChatSummary>>(emptyList())
    override val chats = _chats.asStateFlow()

    override fun loadChats() {
        // Simulate network fetch
        _chats.value = listOf(
            ChatSummary("1", "Prateek Saini", "Of course, what can I help you...", "2 min ago", 3),
            ChatSummary("2", "Gunagyam Sharma", "Okay, let's connect in sometime", "06:21", 4),
            ChatSummary("3", "Jagjit Singh", "Okay, I will work on that.", "Yesterday", 0),
            ChatSummary("4", "Sumit Choudhary", "Goa ki ticket karwao, jaldi!", "Yesterday", 0)
        )
    }

    // You could also track active conversation messages here
    private val _activeConversation = MutableStateFlow<List<Message>>(emptyList())
    override val activeConversation = _activeConversation.asStateFlow()

    override fun loadMessagesFor(chatId: String) {
        // Fetch messages for specific chat ID
        _activeConversation.value = listOf(
            Message(sender = "user", content = "Hi Shashi, good morning!! \uD83D\uDC4B", type =  MessageType.CHAT, timestamp =  "06:02", ),
            Message(sender = "Shashi", content = "Halo! Good Morning, whats up man?", type = MessageType.CHAT, timestamp = "06:12",),
            Message(sender = "user", content = "Sorry to bother. Can i ask you for a help today?", type =  MessageType.CHAT, timestamp =  "06:30", ),
            Message(sender = "Shashi", content = "Of course, what can i help you with??", type = MessageType.CHAT, timestamp =  "06:45",)
        )
    }
}