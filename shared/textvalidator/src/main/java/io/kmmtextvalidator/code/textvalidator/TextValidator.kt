package io.kmmtextvalidator.code.textvalidator

interface TextValidator {
    fun emailValidator(email: String): InputStatus
    fun passwordValidator(minimumCharacters: Int, password: String): InputStatus
}
