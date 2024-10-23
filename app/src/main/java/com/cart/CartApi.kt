package com.cart

import com.cart.dto.CartResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {

    @GET(GET_CART_QUERY)
    suspend fun getCart(
        @Path(KEY_USER_ID) userId: String
    ): Response<CartResponseDTO>

    @POST(UPDATE_CART)
    suspend fun updateCart(@Body map: HashMap<String, Any>): Response<CartResponseDTO>


    companion object {
        private const val KEY_USER_ID = "userId"
        private const val GET_CART_QUERY = "carts/user/{$KEY_USER_ID}"
        private const val UPDATE_CART = "carts/add"

    }
}