package io.kmmtextvalidator.code.textvalidator

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

private const val INVALID_EMAIL_ERROR = "Email is not valid"
private const val EMAIL_REQUIRED_ERROR = "Email is required"

@RunWith(Parameterized::class)
class EmailTextValidatorTest(
    private val givenValue: String,
    private val expectedState: InputStatus
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(
            name = "Given {0} email" +
                "when validate " +
                "then returns {1} validation result"
        )
        fun data(): Collection<Array<*>> = listOf(
            parameters(
                givenValue = "",
                expectedState = InputStatus(EMAIL_REQUIRED_ERROR, false)
            ),
            parameters(
                givenValue = "easy@",
                expectedState = InputStatus(INVALID_EMAIL_ERROR, false)
            ),
            parameters(
                givenValue = "@gmail.com",
                expectedState = InputStatus(INVALID_EMAIL_ERROR, false)
            ),
            parameters(
                givenValue = "easy@gmail",
                expectedState = InputStatus(INVALID_EMAIL_ERROR, false)
            ),
            parameters(
                givenValue = "easy@gmail.",
                expectedState = InputStatus(INVALID_EMAIL_ERROR, false)
            ),
            parameters(
                givenValue = "easy@gmail.co.",
                expectedState = InputStatus(INVALID_EMAIL_ERROR, false)
            ),
            parameters(
                givenValue = "easy@gmail.co.uk",
                expectedState = InputStatus("", true)
            ),
            parameters(
                givenValue = "easy@gmail.com",
                expectedState = InputStatus("", true)
            ),
            parameters(
                givenValue = "easy+more@gmail.com",
                expectedState = InputStatus("", true)
            )
        )

        private fun parameters(
            givenValue: String,
            expectedState: InputStatus
        ) = arrayOf(
            givenValue,
            expectedState
        )
    }

    private lateinit var classUnderTest: EmailTextValidator

    @Before
    fun setUp() {
        classUnderTest = EmailTextValidator()
    }

    @Test
    fun `Given email string when validate then returns expected state`() {

        // When
        val actualState = classUnderTest.validate(givenValue)

        // Then
        assertEquals(expectedState, actualState)
    }
}
