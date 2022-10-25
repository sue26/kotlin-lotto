package calculator.splitter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CustomDelimiterSplitterTest {
    @Test
    fun `If a custom delimiter is not given, isApplicable is false`() {
        assertThat(CustomDelimiterSplitter.isApplicable("1,2:3")).isFalse
    }

    @Test
    fun `If a custom delimiter is given, isApplicable is true`() {
        assertThat(CustomDelimiterSplitter.isApplicable("//&\n1&2")).isTrue
    }

    @Test
    fun `A custom delimiter can be used to separate numbers`() {
        assertThat(CustomDelimiterSplitter.trySplit("//&\n1&2&3")).isEqualTo(listOf("1", "2", "3"))
    }
}
