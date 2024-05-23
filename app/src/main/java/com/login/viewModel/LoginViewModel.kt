package com.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.login.api.Auth
import com.login.loginevent.LoginEvent
import com.login.model.LoginResponse
import com.login.model.LoginUI
import com.utils.errorLogger.ErrorLogger
import com.utils.network.ApiResponse
import com.utils.redux.ApplicationState
import com.utils.redux.Store
import com.utils.sharedPrefs.AppPrefs
import com.utils.useCases.validateEmail
import com.utils.useCases.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: Auth,
    private val store: Store<ApplicationState>,
    val error: ErrorLogger,
    private val appPrefs: AppPrefs
) : ViewModel() {
    private val _loading = MutableLiveData<LoginError>()
    val login: LiveData<LoginError> = _loading

    private val alert = MutableLiveData<String>()
    val alertMsg: LiveData<String> = alert

    private val map: HashMap<String, String> = HashMap()
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.onEmailEvent -> {
                map["email"] = event.email
            }

            LoginEvent.onLoginButtonClicked -> validateAndLogin()

            is LoginEvent.onPasswordEvent -> {
                map["password"] = event.password
            }
        }
    }

    private fun validateAndLogin() {
        val email = map["email"] ?: ""
        val password = map["password"] ?: ""

        val hasError =
            listOf(email.validateEmail(), password.validatePassword()).any { !it.success }
        Log.d(TAG, "validateAndLogin: $hasError")

        email.validateEmail().error?.apply {
            alert.value = this
        }
        password.validatePassword().error?.apply {
            alert.value = this
        }

        if (!hasError) {
            login()
        }

    }

    private fun login() {
        Log.d(TAG, "loginMap: $map")
        _loading.value = LoginError(isLoading = true)
        viewModelScope.launch {
            when (val loginRes = auth.login(map)) {
                is ApiResponse.Failed<*> -> {
                    alert.value = loginRes.errorMsg.toString()
                    _loading.value =
                        LoginError(isLoading = false, error = loginRes.errorMsg.toString())
                }

                is ApiResponse.Success<*> -> {
                    val loginData = loginRes.responseData as LoginResponse
                    appPrefs.saveValue("token", loginData.token)
                    alert.value = "logged In Successfully !!"
                    store.update {
                        it.copy(
                            loginRes = loginData,
                            token = loginData.token,

                            )
                    }
                    _loading.value = LoginError(
                        isLoading = false,
                        success = true,
                        error = null
                    )

                }
            }
        }
    }


    val loginUI = combine(store.stateFlow.map { it.loginRes },
        store.stateFlow.map { it.token }) { loginRes, token ->
        LoginUI(

        )
    }
}

data class LoginError(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)