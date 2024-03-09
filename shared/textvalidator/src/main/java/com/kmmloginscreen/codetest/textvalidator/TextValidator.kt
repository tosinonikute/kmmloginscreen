package com.kmmloginscreen.textvalidator

interface TextValidator {
    fun emailValidator(email: String): InputStatus
    fun passwordValidator(minimumCharacters: Int, password: String): InputStatus
}
