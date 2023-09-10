package com.kmmloginscreen.domain.repository

import com.kmmloginscreen.domain.model.EmailValidationStatusDomainModel
import com.kmmloginscreen.domain.model.PasswordValidationStatusDomainModel
import kotlinx.coroutines.flow.Flow

interface ValidationStatusRepository {
    fun validateEmail(email: String): Flow<EmailValidationStatusDomainModel>
    fun validatePassword(password: String): Flow<PasswordValidationStatusDomainModel>
}
