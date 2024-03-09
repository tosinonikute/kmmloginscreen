package com.kmmloginscreen.domain.usecase

import com.kmmloginscreen.domain.base.ContinuousExecuteUseCase
import com.kmmloginscreen.domain.base.CoroutineContextProvider
import com.kmmloginscreen.domain.model.EmailValidationStatusDomainModel
import com.kmmloginscreen.domain.repository.ValidationStatusRepository

abstract class ValidateEmailUsecase(
    coroutineContextProvider: CoroutineContextProvider
): ContinuousExecuteUseCase<String, EmailValidationStatusDomainModel>(
   coroutineContextProvider
)

class ValidateEmailUsecaseImpl(
    coroutineContextProvider: CoroutineContextProvider,
    private val validationStatusRepository: ValidationStatusRepository
) : ValidateEmailUsecase(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: String,
        callback: (EmailValidationStatusDomainModel) -> Unit
    ) = validationStatusRepository.validateEmail(email = request).collect(callback)
}
