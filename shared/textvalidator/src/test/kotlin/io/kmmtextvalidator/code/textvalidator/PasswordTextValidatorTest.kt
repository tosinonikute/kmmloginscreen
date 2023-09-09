package io.kmmtextvalidator.code.textvalidator

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.MethodRule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.junit.MockitoJUnit

private const val INVALID_CHARACTERS_ERROR = "Password contains invalid characters"
private const val INVALID_PATTERN_ERROR = "Password needs to have at least: one uppercase character, one lowercase character, one number and one special character"
private const val PASSWORD_REQUIRED_ERROR = "Password is required"
private const val PASSWORD_MINIMUM_CHARACTER_ERROR = "Minimum 7 characters"
private const val TWO_SPACES = "  "
private const val ONE_SPACE = " "

@RunWith(Parameterized::class)
class PasswordTextValidatorTest(
    private val givenValue: String,
    private val givenCharacterLimit: Int,
    private val expectedState: InputStatus
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Given {0} password, {1} character limit" +
                "when validate " +
                "then returns {2} validation result"
        )
        fun data(): Collection<Array<*>> = listOf(
            parameters(
                password = "",
                validationState = InputStatus(PASSWORD_REQUIRED_ERROR, false)
            ),
            parameters(
                password = "easy",
                validationState = InputStatus(PASSWORD_MINIMUM_CHARACTER_ERROR, false)
            ),
            parameters(
                password = "Abc@1defgh",
                validationState = InputStatus("", true)
            ),
            parameters(
                password = "Easy$ONE_SPACE#Password123",
                validationState = InputStatus(INVALID_CHARACTERS_ERROR, false)
            ),
            parameters(
                password = "${ONE_SPACE}Easy#Password123",
                validationState = InputStatus(INVALID_CHARACTERS_ERROR, false)
            ),
            parameters(
                password = "Easy#Password123$ONE_SPACE",
                validationState = InputStatus(INVALID_CHARACTERS_ERROR, false)
            ),
            parameters(
                password = "Easy$TWO_SPACES#Password123",
                validationState = InputStatus(INVALID_CHARACTERS_ERROR, false)
            ),
            parameters(
                password = "${TWO_SPACES}Easy#Password123",
                validationState = InputStatus(INVALID_CHARACTERS_ERROR, false)
            ),
            parameters(
                password = "Easy#Password123$TWO_SPACES",
                validationState = InputStatus(INVALID_CHARACTERS_ERROR, false)
            ),
            parameters(
                password = "easypasswordNoPattern",
                validationState = InputStatus(INVALID_PATTERN_ERROR, false)
            )
        )

        private fun parameters(
            password: String,
            characterLimit: Int = 7,
            validationState: InputStatus
        ) = arrayOf(
            password,
            characterLimit,
            validationState
        )
    }

    @get:Rule
    val mockitoRule: MethodRule = MockitoJUnit.rule()

    private lateinit var classUnderTest: PasswordTextValidator

    @Before
    fun setUp() {
        classUnderTest = PasswordTextValidator(
            givenCharacterLimit
        )
    }

    @Test
    fun `Given password string, character limit when validate then returns expected state`() {
        // When
        val actualState = classUnderTest.validate(givenValue)

        // Then
        assertEquals(expectedState, actualState)
    }
}
