package com.example.convo.ui.chatdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.convo.core.session.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.example.convo.domain.model.Message
import com.example.convo.domain.model.MessageType
import com.example.convo.domain.repository.ChatRepository
import com.example.convo.domain.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChatDetailViewModel(
    savedStateHandle: SavedStateHandle, // Used to pass the value of the recipient name to the viewModel
    private val userSession: UserSession,
    private val chatRepository: ChatRepository,
) : ViewModel() {

    // holds the name of the username of the current user
    private val currentUser: String? = userSession.username.value

    // holds the name of the person with the user is chatting
    private val currentRecipient: String = checkNotNull(savedStateHandle["recipientName"])

    // backing property for the list of all the messages with the recipient
    private val _messages = chatRepository.observeMessagesFor(currentRecipient)
        .map{ list ->
            list.map {
                MessageBubbleUiState(
                    content = it.content,
                    type = it.type,
                    timeStamp = it.timestamp,
                    isMe = it.sender == currentUser
                )
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    val messages = _messages

    // backing property for the text that the user is currently typing
    private val _messageText = MutableStateFlow("")
    val messageText = _messageText.asStateFlow()

    // TODO: Implement init function after websockets are introduced
//    init {
//        chatRepository.loadMessagesFor(currentRecipient)
//    }

    // function which tracks what users types in real time
    fun onMessageChange(text: String) {
        _messageText.value = text
    }

    // Function called on pressing the send button
    fun sendMessage() {
        val currentText = _messageText.value
        if (currentText.isBlank() || currentUser.isNullOrEmpty()) return

        val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

        // Create object matching your Server structure
        val newMessage = Message(
            sender = currentUser,
            content = currentText,
            timestamp = currentTime,
            type = MessageType.CHAT
        )

        chatRepository.sendMessage(newMessage)
        _messageText.value = ""
        // TODO: Send 'newMessage' JSON via WebSocket here
    }

}


