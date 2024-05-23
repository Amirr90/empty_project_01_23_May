package com.login.api

import com.utils.network.ApiResponse

interface Auth {
    suspend fun login(map: HashMap<String, String>): ApiResponse
}