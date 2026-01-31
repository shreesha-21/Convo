package com.example.convo.domain.model

data class ChatSummary(
    val id: String? = null,
    val sender: String,
    val lastMessage: String? = null,
    val timeStamp: String,
    val unreadCount: Int,
    val avatarUrl: String? = null
)