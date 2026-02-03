package com.example.convo

import android.app.Application
import com.example.convo.core.di.AppContainer

class ChatApplication : Application() {
    // Instance accessible to the whole app
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}