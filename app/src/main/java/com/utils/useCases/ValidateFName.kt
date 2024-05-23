package com.utils.useCases

class ValidateFName {
    fun invoke(value: String): Result {
        return if (value.length < 7) {
            Result(success = false, error = "First name Should be  longer than 8 digits")
        } else Result(success = true, error = null)
    }
}