package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `Can produce six random numbers`() {
        val lottoNumbers = Lotto().numbers.list

        assertThat(lottoNumbers).hasSize(6)
    }

    @Test
    fun `Can be composed of six custom numbers`() {
        val lottoNumbers = Lotto(listOf(1, 2, 3, 4, 5, 6)).numbers.list

        assertThat(lottoNumbers).hasSize(6)
    }
}
