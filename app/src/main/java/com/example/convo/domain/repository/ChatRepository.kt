package com.example.convo.domain.repository

import com.example.convo.domain.model.ChatSummary
import com.example.convo.domain.model.Message
import kotlinx.coroutines.flow.StateFlow

// Interface for repository related to chats feature
interface ChatRepository {
    // Tracks the list of chats
    fun observeChats(): StateFlow<List<ChatSummary>>

    // Loads the messages with a particular chat Id
    fun observeMessagesFor(chatId: String): StateFlow<List<Message>>

    // Loads stories
    fun observeStories(): StateFlow<List<String?>>

    fun sendMessage(message: Message)
}