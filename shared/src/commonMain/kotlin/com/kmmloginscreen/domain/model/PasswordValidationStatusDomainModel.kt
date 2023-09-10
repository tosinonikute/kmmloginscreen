package com.kmmloginscreen.domain.model

sealed class PasswordValidationStatusDomainModel {
    object Valid : PasswordValidationStatusDomainModel()
    data class InValid(
        val errorMessage: String
    ) : PasswordValidationStatusDomainModel()
}
