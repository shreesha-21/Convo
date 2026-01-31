package com.example.convo.ui.chatlist

import androidx.lifecycle.ViewModel
import com.example.convo.domain.model.ChatSummary
import com.example.convo.ui.chatlist.c.StoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatListViewModel : ViewModel() {

    //  contains the list of chats with other users
    private val _chats = MutableStateFlow<List<ChatSummary>>(emptyList())
    val chats = _chats.asStateFlow()

    // contains the list of stories posted with other users
    private val _stories = MutableStateFlow<List<String>>(emptyList())
    val stories = _stories.asStateFlow()

    // TODO: Load real chats and stories in the future
    init {
        loadMockChats()
        loadMockStories()
    }

    // Function to load mock chats
    private fun loadMockChats() {
        _chats.value = listOf(
            ChatSummary("1", "Prateek Saini", "Of course, what can I help you...", "2 min ago", 3),
            ChatSummary("2", "Gunagyam Sharma", "Okay, let's connect in sometime", "06:21", 4),
            ChatSummary("3", "Jagjit Singh", "Okay, I will work on that.", "Yesterday", 0),
            ChatSummary("4", "Sumit Choudhary", "Goa ki ticket karwao, jaldi!", "Yesterday", 0)
        )
    }

    private fun loadMockStories() {
        _stories.value = listOf<String>("Prateek", "Raj", "Simran", "Virat")
    }
}