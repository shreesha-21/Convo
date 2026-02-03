package com.example.convo.core.di

import com.example.convo.data.repository.MockChatRepository
import com.example.convo.data.repository.MockUserRepository


class MockAppContainer {
    val UserRepository = MockUserRepository()
    val ChatRepository = MockChatRepository()
}