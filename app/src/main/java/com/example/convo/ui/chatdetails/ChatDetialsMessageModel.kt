package com.example.convo.ui.chatdetails

import com.example.convo.domain.model.Message
import com.example.convo.domain.model.MessageType

data class ChatDetialsMessageModel(
    val content: String,
    val sender: String,
    val type: MessageType,
    val timeStamp: String? = null,
    val isMe: Boolean
)

// Extension function to convert message to ChatDetailsMessage
fun Message.toChatDetailsMessage(): ChatDetialsMessageModel {
    return ChatDetialsMessageModel(
        sender = this.sender,
        content = this.content,
        timeStamp = this.timestamp,
        type = this.type,
        isMe = sender == "currentUser"
    )
}
