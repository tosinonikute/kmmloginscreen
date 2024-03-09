package com.kmmloginscreen.textvalidator

class TextValidatorImpl : TextValidator {

    override fun emailValidator(email: String): InputStatus {
        return EmailTextValidator().validate(email)
    }

    override fun passwordValidator(minimumCharacters: Int, password: String): InputStatus {
        return PasswordTextValidator(minimumCharacters).validate(password)
    }
}
