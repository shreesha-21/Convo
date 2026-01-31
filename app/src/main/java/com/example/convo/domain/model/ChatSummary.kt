package com.example.convo.domain.model

data class ChatSummary(
    val id: String,
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int,
    val avatarUrl: String? = null
)