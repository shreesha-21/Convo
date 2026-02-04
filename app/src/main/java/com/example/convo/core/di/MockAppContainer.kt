package com.example.convo.core.di

import com.example.convo.core.session.UserSession
import com.example.convo.data.repository.MockChatRepository
import com.example.convo.data.repository.MockUserRepository

// Mock container which holds the properties required across the application
class MockAppContainer {
    val UserRepository = MockUserRepository()
    val ChatRepository = MockChatRepository()
    val userSession = UserSession()
}