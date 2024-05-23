package com.login.api

import com.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(
        @Body
        body: Map<String, String>
    ): Response<LoginResponse>
}