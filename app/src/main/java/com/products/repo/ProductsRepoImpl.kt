package com.products.repo

import com.products.api.ProductApi
import com.utils.network.ApiResponse
import com.utils.network.responseHandler.ResponseHandler
import java.lang.Exception
import javax.inject.Inject

class ProductsRepoImpl @Inject constructor(
    val api: ProductApi,
    private val handler: ResponseHandler
) : ProductsRepo {
    override suspend fun fetchProducts(queryMap: HashMap<String, Any>): ApiResponse {
        return try {
            val result = api.getAllProduct(queryMap)
            handler.handleResponse(result)
        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = ex.localizedMessage ?: "")
        }
    }

    override suspend fun fetchProducts(productId: String): ApiResponse {
        return try {
            val result = api.fetchProductFromId(productId)
            handler.handleResponse(result)
        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = ex.localizedMessage ?: "")
        }
    }

    override suspend fun fetchProductById(productId: String): ApiResponse {
        return ApiResponse.Failed(responseData = null, errorMsg = "")
    }

    override suspend fun searchProduct(query: String): ApiResponse {
        return ApiResponse.Failed(responseData = null, errorMsg = "")
    }

    override suspend fun fetchCategory(): ApiResponse {
        return try {
            val result = api.fetchCategory()
            handler.handleResponse(result)
        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = ex.localizedMessage ?: "")
        }
    }

    override suspend fun fetchProductBrCategory(categoryId: String): ApiResponse {
        return try {
            val result = api.getProductByCategory(categoryId)
            handler.handleResponse(result)
        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = ex.localizedMessage ?: "")
        }
    }
}