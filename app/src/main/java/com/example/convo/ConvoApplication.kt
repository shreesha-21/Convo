package com.example.convo

import android.app.Application
import com.example.convo.core.di.MockAppContainer

// TODO: Replace the Mock Container with real one
class ChatApplication : Application() {
    // Instance accessible to the whole app
    lateinit var container: MockAppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = MockAppContainer()
    }
}