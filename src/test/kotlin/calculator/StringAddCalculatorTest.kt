package calculator

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.RuntimeException

class StringAddCalculatorTest {
    @ParameterizedTest
    @NullAndEmptySource
    fun `If an input is empty or null, return 0`(input: String?) {
        assertThat(StringAddCalculator.add(input)).isZero
    }

    @Test
    fun `If an input is a number, return its int value`() {
        assertThat(StringAddCalculator.add("3")).isEqualTo(3)
    }

    @Test
    fun `If an input is a sequence of numbers, return their sum (comma as a delimiter)`() {
        assertThat(StringAddCalculator.add("3,5")).isEqualTo(8)
    }

    @Test
    fun `If an input is a sequence of numbers, return their sum (colon as a delimiter)`() {
        assertThat(StringAddCalculator.add("3:5")).isEqualTo(8)
    }

    @Test
    fun `If an input is a sequence of numbers, return their sum (comma and colon as delimiters)`() {
        assertThat(StringAddCalculator.add("1,2:3")).isEqualTo(6)
    }

    @Test
    fun `If an input is a sequence of numbers, return their sum (custom delimiter)`() {
        assertThat(StringAddCalculator.add("//!\n1!2!3")).isEqualTo(6)
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "-1,2", "1:-2", "//!\n1!-2"])
    fun `If there is a negative number, RuntimeException is thrown`(input: String) {
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { StringAddCalculator.add(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["a,2:3", "//!\n1!a"])
    fun `If there is a NaN, RuntimeException is thrown`(input: String) {
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { StringAddCalculator.add(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1+2", "//+\n1&2"])
    fun `If usage of a custom delimiter is incorrect, RuntimeException is thrown`(input: String) {
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { StringAddCalculator.add(input) }
    }
}
