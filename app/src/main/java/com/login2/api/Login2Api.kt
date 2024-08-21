package com.login2.api

import com.login2.dto.LoginResponseDTO
import com.utils.interceptor.Authentication
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Login2Api {
    @POST
    suspend fun login(
        @Body
        body: HashMap<String, Any>
    ): Response<LoginResponseDTO>
}