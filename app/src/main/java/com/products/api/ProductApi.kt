package com.products.api

import com.products.dto.Product
import com.products.dto.ProductResponseDTO
import com.products.dto.category.ProductCategoryResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ProductApi {
    @GET("products")
    suspend fun getAllProduct(
        @QueryMap
        map: HashMap<String, Any> = HashMap()
    ): Response<ProductResponseDTO>

    @GET("products")
    suspend fun getAllProduct(): Response<ProductResponseDTO>

    @GET("products/categories")
    suspend fun fetchCategory(): Response<ProductCategoryResponseDTO>

    @GET("products/category/{category}")
    suspend fun getProductByCategory(
        @Path("category") categoryId: String
    ): Response<ProductResponseDTO>

    @GET("products/{productId}")
    suspend fun fetchProductFromId(
        @Path("productId")
        productId: String
    ): Response<Product>

}