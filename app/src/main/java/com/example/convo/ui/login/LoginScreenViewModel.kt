package com.example.convo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()
    private val _loginEvent = Channel<LoginEvent>()
    val uiEvent  = _loginEvent.receiveAsFlow()

    fun onUsernameChange(newName: String) {
        _username.value = newName
    }

    // TODO: Implement real login function here
    fun login() {
        viewModelScope.launch {
            delay(1000)
            _loginEvent.send(LoginEvent.NavigateToChatList(username.value))
        }
    }

    sealed class LoginEvent {
        data class NavigateToChatList(val username: String): LoginEvent()
        data class ShowSnackbar(val message: String) : LoginEvent()
    }

}