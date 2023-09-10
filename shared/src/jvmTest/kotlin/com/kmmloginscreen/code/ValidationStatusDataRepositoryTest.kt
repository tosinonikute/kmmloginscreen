package com.kmmloginscreen.code

import com.kmmloginscreen.data.repository.ValidationStatusDataRepository
import com.kmmloginscreen.domain.model.EmailValidationStatusDomainModel
import com.kmmloginscreen.domain.model.PasswordValidationStatusDomainModel
import io.kmmtextvalidator.code.textvalidator.InputStatus
import io.kmmtextvalidator.code.textvalidator.TextValidator
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidationStatusDataRepositoryTest {

    @Mock
    private lateinit var textValidator: TextValidator

    private lateinit var classUnderTest: ValidationStatusDataRepository

    @Before
    fun setup() {
        classUnderTest = ValidationStatusDataRepository(
            textValidator = textValidator
        )
    }

    @Test
    fun `Given valid email When validateEmail Then return expected domain model`() = runBlocking {
        // Given
        val validEmail = "Abc@gmail.com"
        val expectedResult = EmailValidationStatusDomainModel.Valid
        given(textValidator.emailValidator(validEmail)).willReturn(InputStatus("", true))

        // When
        var actualResult: EmailValidationStatusDomainModel? = null
        classUnderTest.validateEmail(validEmail).collect { result ->
            actualResult = result
        }

        // Then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given invalid email When validateEmail Then return expected domain model`() = runBlocking {
        // Given
        val invalidEmail = "Abc@gmail.com"
        val errorString = "Email is not valid"
        val expectedResult = EmailValidationStatusDomainModel.InValid(errorString)
        given(textValidator.emailValidator(invalidEmail)).willReturn(InputStatus(errorString, false))

        // When
        var actualResult: EmailValidationStatusDomainModel? = null
        classUnderTest.validateEmail(invalidEmail).collect { result ->
            actualResult = result
        }

        // Then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given valid password When validatePassword Then return expected domain model`() = runBlocking {
        // Given
        val validPassword = "123Abc@"
        val minimumCharacters = 7
        val expectedResult = PasswordValidationStatusDomainModel.Valid
        given(textValidator.passwordValidator(minimumCharacters, validPassword)).willReturn(InputStatus("", true))

        // When
        var actualResult: PasswordValidationStatusDomainModel? = null
        classUnderTest.validatePassword(validPassword).collect { result ->
            actualResult = result
        }

        // Then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given less than minimum characters When validatePassword Then return expected domain model`() = runBlocking {
        // Given
        val lessThanMinimumCharPassword = "123Abc"
        val minimumCharacters = 7
        val errorString = "Minimum 7 characters"
        val expectedResult = PasswordValidationStatusDomainModel.InValid(errorString)
        given(textValidator.passwordValidator(minimumCharacters, lessThanMinimumCharPassword)).willReturn(InputStatus(errorString, false))

        // When
        var actualResult: PasswordValidationStatusDomainModel? = null
        classUnderTest.validatePassword(lessThanMinimumCharPassword).collect { result ->
            actualResult = result
        }

        // Then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given invalid pattern password When validatePassword Then return expected domain model`() = runBlocking {
        // Given
        val invalidPatternPassword = "1234Abc"
        val minimumCharacters = 7
        val errorString = "Password needs to have at least: one uppercase character, one lowercase character, one number and one special character"
        val expectedResult = PasswordValidationStatusDomainModel.InValid(errorString)
        given(textValidator.passwordValidator(minimumCharacters, invalidPatternPassword)).willReturn(InputStatus(errorString, false))

        // When
        var actualResult: PasswordValidationStatusDomainModel? = null
        classUnderTest.validatePassword(invalidPatternPassword).collect { result ->
            actualResult = result
        }

        // Then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given invalid characters password When validatePassword Then return expected domain model`() = runBlocking {
        // Given
        val invalidCharactersPassword = " 1234Abc "
        val minimumCharacters = 7
        val errorString = "Password contains invalid characters"
        val expectedResult = PasswordValidationStatusDomainModel.InValid(errorString)
        given(textValidator.passwordValidator(minimumCharacters, invalidCharactersPassword)).willReturn(InputStatus(errorString, false))

        // When
        var actualResult: PasswordValidationStatusDomainModel? = null
        classUnderTest.validatePassword(invalidCharactersPassword).collect { result ->
            actualResult = result
        }

        // Then
        assertEquals(expectedResult, actualResult)
    }
}
