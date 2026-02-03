package com.example.convo.ui.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.convo.domain.model.ChatSummary
import com.example.convo.domain.repository.ChatRepository
import com.example.convo.ui.chatlist.components.ChatSelectionEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatListViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    //  contains the list of chats with other users
    private val _chats = chatRepository.observeChats() // Automatically subscribes to the flow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    val chats = _chats

    // contains the list of stories posted with other users
    private val _stories = chatRepository.observeStories()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    val stories = _stories

    // Event broadcasted to send intent to compose so that navigation can be made
    private val _chatSelectionEvent = Channel<ChatSelectionEvent>()
    val chatSelectionEvent = _chatSelectionEvent.receiveAsFlow()

    // TODO: Initialization required when websocket is implemented
//    init {
//        chatRepository.loadChats()
//        chatRepository.loadStories() // TODO: Create implementation for this
//    }

    // function called when user clicks on a chat
    fun onChatClick(chatName: String) {
        viewModelScope.launch {
            _chatSelectionEvent.send(ChatSelectionEvent.NavigateToChatDetail(chatName))
        }
    }

}

