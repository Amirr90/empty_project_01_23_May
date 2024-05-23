package com.utils.useCases

class ValidateLName {
    fun invoke(value: String): Result {
        return if (value.length < 7) {
            Result(success = false, error = "Last name Should be  longer than 8 digits")
        } else Result(success = true, error = null)
    }


}