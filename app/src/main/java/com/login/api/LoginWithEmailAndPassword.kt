package com.login.api

import com.utils.network.ApiResponse
import javax.inject.Inject

class LoginWithEmailAndPassword @Inject constructor(
    private val api: LoginApi
) : Auth {
    override suspend fun login(map: HashMap<String, String>): ApiResponse {
        return try {
            val request = api.login(map)

            when {
                request.isSuccessful && request.code() == 200 -> {
                    return ApiResponse.Success(responseData = request.body())
                }

                else -> {
                    return ApiResponse.Failed(responseData = null, errorMsg = request.code())
                }
            }
        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = ex.localizedMessage ?: "")
        }
    }

}