package com.kmmloginscreen.data.repository

import com.kmmloginscreen.domain.model.EmailValidationStatusDomainModel
import com.kmmloginscreen.domain.model.PasswordValidationStatusDomainModel
import com.kmmloginscreen.domain.repository.ValidationStatusRepository
import io.kmmtextvalidator.code.textvalidator.TextValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private const val MINIMUM_CHARACTERS = 7

class ValidationStatusDataRepository(
    private val textValidator: TextValidator
) : ValidationStatusRepository {
    override fun validateEmail(email: String): Flow<EmailValidationStatusDomainModel> {
        val validatedEmail = textValidator.emailValidator(email)
        return if (textValidator.emailValidator(email).isValid) {
            flowOf(EmailValidationStatusDomainModel.Valid)
        } else {
            flowOf(EmailValidationStatusDomainModel.InValid(validatedEmail.error))
        }
    }

    override fun validatePassword(password: String): Flow<PasswordValidationStatusDomainModel> {
        val validatedPassword = textValidator.passwordValidator(MINIMUM_CHARACTERS, password)
        return if (validatedPassword.isValid) {
            flowOf(PasswordValidationStatusDomainModel.Valid)
        } else {
            flowOf(PasswordValidationStatusDomainModel.InValid(validatedPassword.error))
        }
    }
}
