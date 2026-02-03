package com.example.convo.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    val currentUser: StateFlow<String?>
    fun login(username: String)
    fun logout()
}