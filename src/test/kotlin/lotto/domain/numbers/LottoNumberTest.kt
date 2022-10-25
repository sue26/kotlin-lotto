package lotto.domain.numbers

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoNumberTest {
    @Test
    fun `Lotto number is from 1 to 45`() {
        val lottoNumbers = (1..45).map { it }

        Assertions.assertThatNoException().isThrownBy { lottoNumbers.map { LottoNumber.from(it) } }
    }

    @Test
    fun `If a lotto number is less than 1 or greater than 45, IllegalArgumentException is thrown`() {
        val lessThanOneLottoNumber = 0
        val greaterThanFortyFiveLottoNumber = 46

        Assertions.assertThatIllegalArgumentException().isThrownBy { LottoNumber.from(lessThanOneLottoNumber) }
        Assertions.assertThatIllegalArgumentException().isThrownBy { LottoNumber.from(greaterThanFortyFiveLottoNumber) }
    }

    @Test
    fun `LottoNumber instance is created per number`() {
        val lottoNumber1 = LottoNumber.from(2)
        val lottoNumber2 = LottoNumber.from(2)

        assertThat(lottoNumber1).isEqualTo(lottoNumber2)
    }
}
