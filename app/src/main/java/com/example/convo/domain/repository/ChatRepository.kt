package com.example.convo.domain.repository

import com.example.convo.domain.model.ChatSummary
import com.example.convo.domain.model.Message
import kotlinx.coroutines.flow.StateFlow

// Interface for repository related to chats feature
interface ChatRepository {
    val chats: StateFlow<List<ChatSummary>>
    // Loads the list of chats
    fun loadChats()

    val activeConversation: StateFlow<List<Message>>
    // Loads the messages with a particular chat Id
    fun loadMessagesFor(chatId: String)
}