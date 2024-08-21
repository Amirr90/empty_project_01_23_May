package com.products.dto

data class ProductResponseDTO(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)