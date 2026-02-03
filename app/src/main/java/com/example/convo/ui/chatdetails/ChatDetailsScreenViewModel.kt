package com.example.convo.ui.chatdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.example.convo.domain.model.Message
import com.example.convo.domain.model.MessageType
import com.example.convo.domain.repository.ChatRepository
import com.example.convo.domain.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ChatDetailViewModel(
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository,
    private val currentRecipient: String, // We inject the logged-in user's name
) : ViewModel() {

    private val _messages = chatRepository.observeMessagesFor(currentRecipient)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    val messages = _messages

    private val _messageText = MutableStateFlow("")
    val messageText = _messageText.asStateFlow()

    private val currenUser: String? = userRepository.currentUser.value

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
        if (currentText.isBlank() || currenUser.isNullOrEmpty()) return

        val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

        // Create object matching your Server structure
        val newMessage = Message(
            sender = currenUser,
            content = currentText,
            timestamp = currentTime,
            type = MessageType.CHAT
        )

        chatRepository.sendMessage(newMessage)
        // TODO: Send 'newMessage' JSON via WebSocket here
    }
}

