package com.example.convo.ui.chatdetails

import com.example.convo.domain.model.MessageType

data class MessageBubbleUiState(
    val content: String,
    val type: MessageType,
    val timeStamp: String? = null,
    val isMe: Boolean
)
