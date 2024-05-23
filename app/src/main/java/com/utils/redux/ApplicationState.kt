package com.utils.redux

import com.login.model.LoginResponse
import com.login.model.LoginUI


data class ApplicationState(
    val loginRes: LoginResponse = LoginResponse(),
    val token: String = ""
)
