package com.example.convo.core.di

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.convo.ChatApplication
import com.example.convo.ui.chatdetails.ChatDetailViewModel
import com.example.convo.ui.chatlist.ChatListViewModel
import com.example.convo.ui.login.LoginViewModel

// Provider for the view models for app wide features
// Contains the initializer for all the view models
object AppViewModelProvider {
    val factory = viewModelFactory {

        // Initializer for the Login View Model
        initializer {
            val app = (this[APPLICATION_KEY] as ChatApplication)
            LoginViewModel(userRepository = app.container.UserRepository)
        }

        // Initializer for the ChatList ViewModel
        initializer {
            val app = (this[APPLICATION_KEY] as ChatApplication)
            ChatListViewModel(app.container.ChatRepository)
        }

        initializer {
            val app = (this[APPLICATION_KEY] as ChatApplication)
            ChatDetailViewModel(
                userRepository = app.container.UserRepository,
                chatRepository = app.container.ChatRepository,
                currentRecipient = "Placeholder" // this will be updated during navigation
            )
        }
    }
}