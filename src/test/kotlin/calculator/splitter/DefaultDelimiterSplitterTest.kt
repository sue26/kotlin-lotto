package calculator.splitter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultDelimiterSplitterTest {
    @Test
    fun `If a custom delimiter is not given, isApplicable is true`() {
        assertThat(DefaultDelimiterSplitter.isApplicable("1,2:3")).isTrue
    }

    @Test
    fun `If a custom delimiter is given, isApplicable is false`() {
        assertThat(DefaultDelimiterSplitter.isApplicable("//&\n1&2")).isFalse
    }

    @Test
    fun `A default delimiter can be used to separate numbers`() {
        assertThat(DefaultDelimiterSplitter.trySplit("1,2:3")).isEqualTo(listOf("1", "2", "3"))
    }
}
