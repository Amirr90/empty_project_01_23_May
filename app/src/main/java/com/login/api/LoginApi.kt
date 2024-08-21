package com.login.api

import com.login.model.LoginResponse
import com.login2.dto.LoginResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(
        @Body
        body: Map<String, String>
    ): Response<LoginResponse>


    @POST("auth/login")
    suspend fun login(
        @Body
        body: HashMap<String, Any>
    ): Response<LoginResponseDTO>
}