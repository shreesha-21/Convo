package com.example.convo.domain.model

data class Message(
    val content: String,
    val sender: String,
    val type: MessageType,
    val timestamp: String? = null
)

enum class MessageType {
    CHAT, JOIN, LEAVE
}
