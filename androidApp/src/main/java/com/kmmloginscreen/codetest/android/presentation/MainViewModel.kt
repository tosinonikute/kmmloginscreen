package com.kmmloginscreen.android.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import com.kmmloginscreen.android.presentation.base.BaseViewModel
import com.kmmloginscreen.android.presentation.base.UseCaseExecutorProvider
import com.kmmloginscreen.android.presentation.model.MainViewState
import com.kmmloginscreen.domain.model.EmailValidationStatusDomainModel
import com.kmmloginscreen.domain.model.PasswordValidationStatusDomainModel
import com.kmmloginscreen.domain.usecase.ValidateEmailUsecase
import com.kmmloginscreen.domain.usecase.ValidatePasswordUsecase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val validateEmailUsecase: ValidateEmailUsecase,
    private val validatePasswordUsecase: ValidatePasswordUsecase,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<MainViewState>(
    useCaseExecutorProvider
) {
    override fun initialState() = MainViewState()

    fun onValidateEmail(email: String) {
        useCaseExecutor.execute(
            useCase = validateEmailUsecase,
            value = email,
            callback = { validationStatus ->
                when (validationStatus) {
                    is EmailValidationStatusDomainModel.InValid -> {
                        updateState(
                            currentViewState().copy(
                                isEmailValid = false,
                                emailValidationErrorMessage = validationStatus.errorMessage
                            )
                        )
                    }
                    is EmailValidationStatusDomainModel.Valid -> {
                        updateState(currentViewState().copy(isEmailValid = true))
                    }
                }
            },
            onError = { error ->
                updateState(currentViewState().copy(errorFailedString = error.localizedMessage.toString()))
            }
        )
    }

    fun onValidatePassword(password: String) {
        useCaseExecutor.execute(
            useCase = validatePasswordUsecase,
            value = password,
            callback = { validationStatus ->
                when (validationStatus) {
                    is PasswordValidationStatusDomainModel.InValid -> {
                        updateState(
                            currentViewState().copy(
                                isPasswordValid = false,
                                passwordValidationErrorMessage = validationStatus.errorMessage
                            )
                        )
                    }
                    is PasswordValidationStatusDomainModel.Valid -> {
                        updateState(currentViewState().copy(isPasswordValid = true))
                    }
                }
            },
            onError = { error ->
                updateState(currentViewState().copy(errorFailedString = error.localizedMessage.toString()))
            }
        )
    }
}
