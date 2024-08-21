package com.login2.repo

import com.utils.network.ApiResponse

interface Login2Repo {

    suspend fun login(body: HashMap<String, Any>): ApiResponse
}