package com.cart

import com.cart.dto.CartResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {

    @GET("carts/user/{userId}")
    suspend fun getCart(
        @Path("userId") userId: String
    ): Response<CartResponseDTO>

    @POST("carts/add")
    suspend fun updateCart(@Body map: HashMap<String, Any>): Response<CartResponseDTO>
}