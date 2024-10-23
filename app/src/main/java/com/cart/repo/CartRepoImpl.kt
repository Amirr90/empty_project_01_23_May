package com.cart.repo

import android.util.Log
import com.cart.CartApi
import com.utils.network.ApiResponse
import com.utils.network.responseHandler.ResponseHandler
import com.utils.sharedPrefs.AppPrefs
import javax.inject.Inject


const val TAG = "CartRepoImpl"

class CartRepoImpl @Inject constructor(
    private val api: CartApi,
    private val appPrefs: AppPrefs,
    private val handler: ResponseHandler
) : CartRepo {
    override suspend fun getCartItem(): ApiResponse {
        return try {
            Log.d(TAG, "userId: ${appPrefs.userId}")
            val result = api.getCart(appPrefs.userId ?: "")
            handler.handleResponse(result)
        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = ex.localizedMessage ?: "")
        }
    }

    override suspend fun updateCart(map: HashMap<String, Any>): ApiResponse {
        return try {
            Log.d(TAG, "updateCart: $map")
            val result = api.updateCart(map)
            handler.handleResponse(result)
        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = ex.localizedMessage ?: "")
        }

    }
}