package com.cart.repo

import com.utils.network.ApiResponse

interface CartRepo {

    suspend fun getCartItem(): ApiResponse

    suspend fun updateCart(map: HashMap<String, Any>): ApiResponse
}