package com.example.convo.data.repository

import com.example.convo.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

//  Implements the user repository interface
class MockUserRepository : UserRepository {

    private val _currentUser = MutableStateFlow<String?>(null)
    val currentUser = _currentUser.asStateFlow()

    override fun login(username: String) {

//        TODO("Implement this login function")
        _currentUser.value = username

    }

    override fun logout() {
//        TODO("Not yet implemented")
        _currentUser.value = null
    }
}