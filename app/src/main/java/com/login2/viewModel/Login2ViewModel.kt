package com.login2.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.login2.dto.LoginResponseDTO
import com.login2.repo.Login2Repo
import com.utils.network.ApiResponse
import com.utils.sharedPrefs.AppPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Login2ViewModel @Inject constructor(
    private val loginRepo: Login2Repo,
    private val appPrefs: AppPrefs
) : ViewModel() {
    private val map: HashMap<String, Any> = HashMap()

    fun login() {
        map["username"] = "emilys"
        map["password"] = "emilyspass"


        viewModelScope.launch {
            when (val response = loginRepo.login(map)) {
                is ApiResponse.Success<*> -> {
                    manageAndSaveUserToken(response.responseData)
                }

                is ApiResponse.Failed<*> -> {
                    Log.d("LoginFailed", "login: ${response.errorMsg}")
                }
            }
        }
    }

    private fun manageAndSaveUserToken(responseData: Any?) {
        responseData?.let {
            val loginData = it as LoginResponseDTO
            Log.d("login", "loginData: $loginData")

            appPrefs.apply {
                token = loginData.token
                refreshToken = loginData.refreshToken
            }

            Log.d("login", "user Data Saved")


        }


    }
}