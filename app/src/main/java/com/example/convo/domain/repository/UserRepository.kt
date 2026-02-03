package com.example.convo.domain.repository

interface UserRepository {
    fun login(username: String)
    fun logout()
}