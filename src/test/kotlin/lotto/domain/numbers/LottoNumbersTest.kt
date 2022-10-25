package lotto.domain.numbers

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LottoNumbersTest {
    @Nested
    inner class `Quick Pick` {
        @Test
        fun `Six random numbers`() {
            val lottoNumbers = LottoNumbers().list
            assertThat(lottoNumbers).hasSize(6)
        }
    }

    @Nested
    inner class `Custom Numbers` {
        @Test
        fun `Six player-picked numbers`() {
            val customLottoNumbers = listOf(1, 2, 3, 4, 5, 6)
            val lottoNumbers = LottoNumbers(customLottoNumbers)

            assertThat(lottoNumbers.list)
                .hasSize(6)
                .isEqualTo(customLottoNumbers.map { LottoNumber.from(it) })
        }

        @Test
        fun `If a player enters less than or more than six numbers, IllegalArgumentException is thrown`() {
            val moreThanSix = listOf(1, 2, 3, 4, 5, 6, 7)
            val lessThanSix = listOf(1, 2, 3, 4, 5)

            assertThatIllegalArgumentException().isThrownBy { LottoNumbers(moreThanSix) }
            assertThatIllegalArgumentException().isThrownBy { LottoNumbers(lessThanSix) }
        }
    }
}
