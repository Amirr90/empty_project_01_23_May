package com.utils.errorLogger

import android.util.Log
import javax.inject.Inject

class ErrorLogger @Inject constructor() {

    fun logError(error: Any?, tag: String = "ErrorLogger") {
        Log.d(tag, "logError: $error")
    }
}