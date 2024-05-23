package com.utils.network


open class ApiResponse {
    data class Success<ResponseType>(val responseData: ResponseType) : ApiResponse()
    data class Failed<ResponseType>(val responseData: ResponseType? = null, val errorMsg: Any) :
        ApiResponse()
}
