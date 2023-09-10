package com.kmmloginscreen.domain.usecase

import com.kmmloginscreen.domain.base.ContinuousExecuteUseCase
import com.kmmloginscreen.domain.base.CoroutineContextProvider
import com.kmmloginscreen.domain.model.PasswordValidationStatusDomainModel
import com.kmmloginscreen.domain.repository.ValidationStatusRepository

abstract class ValidatePasswordUsecase(
  coroutineContextProvider: CoroutineContextProvider
): ContinuousExecuteUseCase<String, PasswordValidationStatusDomainModel>(
   coroutineContextProvider
)

class ValidatePasswordUsecaseImpl(
    coroutineContextProvider: CoroutineContextProvider,
    private val validationStatusRepository: ValidationStatusRepository
) : ValidatePasswordUsecase(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: String,
        callback: (PasswordValidationStatusDomainModel) -> Unit
    ) = validationStatusRepository.validatePassword(password = request).collect(callback)
}
