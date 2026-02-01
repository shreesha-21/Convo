package com.example.convo.ui.chatlist.components

// Events related to clicking on a chat in chat list
sealed class ChatSelectionEvent {
    data class NavigateToChatDetail(val username: String): ChatSelectionEvent()
    data class FailedToNavigateToChatDetail(val errorMessage: String): ChatSelectionEvent()
}

