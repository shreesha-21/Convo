package com.example.convo.ui.chatdetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.example.convo.domain.model.Message
import com.example.convo.domain.model.MessageType

class ChatDetailViewModel(
    private val currentRecipient: String, // We inject the logged-in user's name
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatDetialsMessageModel>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _messageText = MutableStateFlow("")
    val messageText = _messageText.asStateFlow()

    // TODO: Call the real function here instead of the mock function
    init {
        loadMockMessages()
    }

    // Function to load mock messages
    private fun loadMockMessages(rawMessages: List<Message> = unformattedMockMessagesList) {
        val formattedList = rawMessages.map {
            ChatDetialsMessageModel(
                sender = it.sender,
                content = it.content,
                type = it.type,
                timeStamp = it.timestamp,
                isMe = it.sender != currentRecipient
            )
        }
        _messages.value = formattedList
    }

    // function which tracks what users types in real time
    fun onMessageChange(text: String) {
        _messageText.value = text
    }

    // Function called on pressing the send button
    fun sendMessage() {
        val currentText = _messageText.value
        if (currentText.isBlank()) return

        val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

        // Create object matching your Server structure
        val newMessage = Message(
            sender = "",
            content = currentText,
            timestamp = currentTime,
            type = MessageType.CHAT
        )

        // Update UI immediately (Optimistic update)
        _messages.value = _messages.value + newMessage.toChatDetailsMessage()
        _messageText.value = ""

        // TODO: Send 'newMessage' JSON via WebSocket here
    }
}

//  Raw messages which is used to test the ui
val unformattedMockMessagesList = listOf(
Message(sender = "user", content = "Hi Shashi, good morning!! \uD83D\uDC4B", type =  MessageType.CHAT, timestamp =  "06:02", ),
Message(sender = "Shashi", content = "Halo! Good Morning, whats up man?", type = MessageType.CHAT, timestamp = "06:12",),
Message(sender = "user", content = "Sorry to bother. Can i ask you for a help today?", type =  MessageType.CHAT, timestamp =  "06:30", ),
Message(sender = "Shashi", content = "Of course, what can i help you with??", type = MessageType.CHAT, timestamp =  "06:45",)
)

