package com.login.loginevent

sealed class LoginEvent {
    data class onEmailEvent(val email: String) : LoginEvent()
    data class onPasswordEvent(val password: String) : LoginEvent()
    object onLoginButtonClicked : LoginEvent()
}