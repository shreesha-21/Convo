package com.example.convo.core.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// holds the state of username during a session
class UserSession {

    // backing  property for the username
    private val _username = MutableStateFlow<String?>(null)
    val username = _username.asStateFlow()

    // call during login
    fun setUserName(name: String) {
        _username.value = name
    }

    // call during logout
    fun clearUserName() {
        _username.value = null
    }

}