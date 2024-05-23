package com.utils.network.responseHandler


import com.utils.network.ApiResponse
import retrofit2.Response

interface ResponseHandler {
    fun handleResponse(response: Response<*>): ApiResponse
}