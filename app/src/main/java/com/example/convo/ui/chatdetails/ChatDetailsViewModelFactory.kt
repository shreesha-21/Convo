package com.example.convo.ui.chatdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//
//// Factory required to pass 'currentUser' into the ViewModel constructor
//class ChatDetailViewModelFactory(private val username: String) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ChatDetailViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ChatDetailViewModel(username) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}