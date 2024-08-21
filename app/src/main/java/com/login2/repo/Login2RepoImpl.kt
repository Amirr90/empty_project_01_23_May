package com.login2.repo

import com.login.api.LoginApi
import com.login2.api.Login2Api
import com.utils.network.ApiResponse
import java.lang.Exception
import javax.inject.Inject

class Login2RepoImpl @Inject constructor(
    val api: LoginApi
) : Login2Repo {
    override suspend fun login(body: HashMap<String, Any>): ApiResponse {
        return try {
            val req = api.login(body)
            if (req.isSuccessful && req.code() == 200) {
                ApiResponse.Success(responseData = req.body())
            } else ApiResponse.Failed(responseData = null, errorMsg = "Something went wrong !!")

        } catch (ex: Exception) {
            ApiResponse.Failed(responseData = null, errorMsg = "Something went wrong !!")
        }
    }
}