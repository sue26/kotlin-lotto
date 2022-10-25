package calculator.number

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PositiveNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["a", "1&2", ","])
    fun `If input is not a number, RuntimeException is thrown`(input: String) {
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { PositiveNumber(input).value }
    }

    @Test
    fun `If input is a negative number, RuntimeException is thrown`() {
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { PositiveNumber("-1").value }
    }

    @Test
    fun `Return the int value of a positive number`() {
        val number = PositiveNumber("100").value
        assertThat(number).isEqualTo(100)
    }
}
