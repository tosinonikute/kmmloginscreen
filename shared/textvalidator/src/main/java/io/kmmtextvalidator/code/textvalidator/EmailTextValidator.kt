package io.kmmtextvalidator.code.textvalidator

class EmailTextValidator {

    private val emailRegex = "(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$)".toRegex()

    fun validate(input: String) = when {
        input.isBlank() -> InputStatus("Email is required", false)
        !isValidEmail(input) -> InputStatus("Email is not valid", false)
        else -> InputStatus("", true)
    }

    private fun isValidEmail(input: String) = input.matches(emailRegex)
}
