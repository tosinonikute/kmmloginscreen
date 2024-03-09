package com.kmmloginscreen.textvalidator

private const val NO_MINIMUM_CHARACTERS = -1
private const val CONSECUTIVE_SPACES_VALUE = " "

class PasswordTextValidator(
    private val minimumCharacters: Int = NO_MINIMUM_CHARACTERS
) {
    private val lowerCaseRegex = "(.*[a-z].*)".toRegex()
    private val upperCaseRegex = "(.*[A-Z].*)".toRegex()
    private val numberRegex = "(.*[0-9].*)".toRegex()
    private val symbolRegex = "(.*[^A-Za-z0-9].*)".toRegex()
    private val errorString: String = "Password needs to have at least: one uppercase character, one lowercase character, one number and one special character"
    private val passwordRequiredString: String = "Password is required"
    private val passwordMinimumCharacterString: String = "Minimum $minimumCharacters characters"
    private val passwordInvalidCharacterString: String = "Password contains invalid characters"

    fun validate(input: String) = when {
        input.isBlank() -> InputStatus(passwordRequiredString, false)
        !isValidLength(input) -> { InputStatus(passwordMinimumCharacterString, false) }
        !isPasswordValid(input) -> InputStatus(errorString, false)
        consecutiveSpacesCheck(input) -> InputStatus(passwordInvalidCharacterString, false)
        else -> InputStatus("", true)
    }

    private fun consecutiveSpacesCheck(input: String) = input.contains(CONSECUTIVE_SPACES_VALUE)

    private fun isValidLength(input: String) = minimumCharacters == NO_MINIMUM_CHARACTERS || input.length >= minimumCharacters

    private fun isPasswordValid(password: String): Boolean {
        var matchCount = 0
        if (password.matches(lowerCaseRegex)) {
            matchCount++
        }
        if (password.matches(upperCaseRegex)) {
            matchCount++
        }
        if (password.matches(numberRegex)) {
            matchCount++
        }
        if (password.matches(symbolRegex)) {
            matchCount++
        }
        return matchCount >= 4
    }
}
