package com.utils.network.responseHandler


import com.utils.network.ApiResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun Response<*>.getResponseAfterValidation(): ApiResponse {
    return try {

        return when (this.code()) {
            200 -> {
                val data = this.body() ?: return ApiResponse.Failed(
                    errorMsg = "Failed to get Response " + this.code(), responseData = null
                )
                ApiResponse.Success(data)
            }

            201 -> {
                val data = this.body() ?: return ApiResponse.Failed(
                    errorMsg = "Failed to get Response " + this.code(), responseData = null
                )
                ApiResponse.Success(data)
            }

            else -> ApiResponse.Failed(
                errorMsg = this.message(),
                responseData = null
            )
        }
    } catch (http: HttpException) {
        ApiResponse.Failed(
            errorMsg = "Request Failed !",
            responseData = null
        )
    } catch (io: IOException) {
        ApiResponse.Failed(
            errorMsg = "low or no internet connectivity",
            responseData = null
        )
    }
}