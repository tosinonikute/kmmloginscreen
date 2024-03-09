package com.kmmloginscreen.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.kmmloginscreen.android.presentation.MainViewModel
import com.kmmloginscreen.android.presentation.model.MainViewState
import com.kmmloginscreen.domain.base.BaseUseCase
import com.kmmloginscreen.domain.base.RunningExecution
import com.kmmloginscreen.domain.base.UseCaseExecutor
import com.kmmloginscreen.domain.model.EmailValidationStatusDomainModel
import com.kmmloginscreen.domain.model.PasswordValidationStatusDomainModel
import com.kmmloginscreen.domain.usecase.ValidateEmailUsecase
import com.kmmloginscreen.domain.usecase.ValidatePasswordUsecase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var validateEmailUsecase: ValidateEmailUsecase

    @Mock
    private lateinit var validatePasswordUsecase: ValidatePasswordUsecase

    @Mock
    private lateinit var useCaseExecutor: UseCaseExecutor

    private lateinit var classUnderTest: MainViewModel

    @Before
    fun setup() {
        classUnderTest = MainViewModel(
            validateEmailUsecase = validateEmailUsecase,
            validatePasswordUsecase = validatePasswordUsecase,
            useCaseExecutorProvider = { useCaseExecutor }
        )
    }

    @Test
    fun `Given valid email request when onValidateEmail Then update state`() {
        // Given
        val givenValidEmailRequest = "Abc@gmail.com"
        useCaseExecutor.givenSuccessfulUseCaseExecution(
            useCase = validateEmailUsecase,
            input = givenValidEmailRequest,
            result = EmailValidationStatusDomainModel.Valid
        )

        // When
        classUnderTest.onValidateEmail(email = givenValidEmailRequest)

        // Then
        val actualState = classUnderTest.currentViewState()
        val expectedState = MainViewState(
            isEmailValid = true,
            isPasswordValid = false,
            emailValidationErrorMessage = "",
            passwordValidationErrorMessage = "",
            errorFailedString = ""
        )
        assertEquals(expectedState, actualState)
    }

    @Test
    fun `Given invalid email request when onValidateEmail Then update state`() {
        // Given
        val givenInvalidEmailRequest = "Abc@gmail"
        val errorString = "Email is not valid"
        useCaseExecutor.givenSuccessfulUseCaseExecution(
            useCase = validateEmailUsecase,
            input = givenInvalidEmailRequest,
            result = EmailValidationStatusDomainModel.InValid(errorString)
        )

        // When
        classUnderTest.onValidateEmail(email = givenInvalidEmailRequest)

        // Then
        val actualState = classUnderTest.currentViewState()
        val expectedState = MainViewState(
            isEmailValid = false,
            isPasswordValid = false,
            emailValidationErrorMessage = errorString,
            passwordValidationErrorMessage = "",
            errorFailedString = ""
        )
        assertEquals(expectedState, actualState)
    }

    @Test
    fun `Given valid password request when onValidatePassword Then update state`() {
        // Given
        val givenValidPasswordRequest = "123Abc@"
        useCaseExecutor.givenSuccessfulUseCaseExecution(
            useCase = validatePasswordUsecase,
            input = givenValidPasswordRequest,
            result = PasswordValidationStatusDomainModel.Valid
        )

        // When
        classUnderTest.onValidatePassword(password = givenValidPasswordRequest)

        // Then
        val actualState = classUnderTest.currentViewState()
        val expectedState = MainViewState(
            isEmailValid = false,
            isPasswordValid = true,
            emailValidationErrorMessage = "",
            passwordValidationErrorMessage = "",
            errorFailedString = ""
        )
        assertEquals(expectedState, actualState)
    }

    @Test
    fun `Given invalid password request when onValidatePassword Then update state`() {
        // Given
        val givenInValidPasswordRequest = "123456789"
        val errorString = "Password needs to have at least: one uppercase character, one lowercase character, one number and one special character"
        useCaseExecutor.givenSuccessfulUseCaseExecution(
            useCase = validatePasswordUsecase,
            input = givenInValidPasswordRequest,
            result = PasswordValidationStatusDomainModel.InValid(errorString)
        )

        // When
        classUnderTest.onValidatePassword(password = givenInValidPasswordRequest)

        // Then
        val actualState = classUnderTest.currentViewState()
        val expectedState = MainViewState(
            isEmailValid = false,
            isPasswordValid = false,
            emailValidationErrorMessage = "",
            passwordValidationErrorMessage = errorString,
            errorFailedString = ""
        )
        assertEquals(expectedState, actualState)
    }
}

fun <REQUEST, RESULT> UseCaseExecutor.givenSuccessfulUseCaseExecution(
    useCase: BaseUseCase<REQUEST, RESULT>,
    input: REQUEST,
    result: RESULT
) {
    doAnswer { invocationOnMock ->
        (invocationOnMock.arguments[2] as (RESULT) -> Unit)(result)
        mock<RunningExecution>()
    }.whenever(this).execute(
        useCase = eq(useCase),
        value = eq(input),
        callback = any(),
        onError = any()
    )
}
