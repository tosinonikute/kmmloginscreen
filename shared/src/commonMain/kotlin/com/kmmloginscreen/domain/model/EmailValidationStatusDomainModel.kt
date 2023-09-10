package com.kmmloginscreen.domain.model

sealed class EmailValidationStatusDomainModel {
    object Valid : EmailValidationStatusDomainModel()
    data class InValid(
        val errorMessage: String
    ) : EmailValidationStatusDomainModel()
}
