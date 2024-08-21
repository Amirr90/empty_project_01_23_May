package com.cart.dto

data class CartResponseDTO(
    val carts: List<Cart>,
    val limit: Int,
    val skip: Int,
    val total: Int
)