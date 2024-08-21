package com.cart.ui

import com.products.model.Product

data class CartUI(
    val cartProducts: List<Product> = emptyList(),
    val totalAmount: Double = 0.0
)
