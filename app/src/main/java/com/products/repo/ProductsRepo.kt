package com.products.repo

import com.utils.network.ApiResponse

interface ProductsRepo {
    suspend fun fetchProducts(queryMap: HashMap<String, Any>): ApiResponse
    suspend fun fetchProducts(productId: String): ApiResponse
    suspend fun fetchProductById(productId: String): ApiResponse
    suspend fun searchProduct(query: String): ApiResponse
    suspend fun fetchCategory(): ApiResponse
    suspend fun fetchProductBrCategory(categoryId: String): ApiResponse
}