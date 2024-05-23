package com.utils.useCases

class ValidateUserName {
    fun invoke(username: String): Result {

        val isValidPassword = username.any {
            it.isDigit()
        } && username.any {
            it.isLetter()
        } && username.any {
            it.isLowerCase()
        } && username.any {
            it.isUpperCase()
        } && username.any {
            it.isUpperCase()
        }
        return when {
            username.isEmpty() -> {
                Result(
                    success = false,
                    error = "username is empty !!"
                )
            }
            username.any {
                it.isDigit()
            } -> {
                Result(
                    success = false,
                    error = "username contain digit, not allowed !!"
                )
            }

            else -> Result(
                success = true
            )
        }
    }

}