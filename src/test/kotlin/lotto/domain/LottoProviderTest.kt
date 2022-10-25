package lotto.domain

import lotto.domain.numbers.LottoNumbers
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoProviderTest {
    @Nested
    @DisplayName("No custom lottery tickets")
    inner class WithoutCustomLotto {
        @Test
        fun `A lottery ticket costs 1000 won`() {
            val price = LottoProvider.LOTTO_PRICE

            assertThat(price).isEqualTo(1000)
        }

        @Test
        fun `Total number of lottery tickets is calculated based on the amount given`() {
            val payment = 14300

            assertThat(LottoProvider(payment).numberOfAutomaticLottos).isEqualTo(14)
        }

        @Test
        fun `# of quick pick lottery tickets should equal to the total # of lottery tickets`() {
            val payment = 20500
            val provider = LottoProvider(payment)

            assertThat(provider.lottos).hasSize(provider.numberOfAutomaticLottos)
        }

        @Test
        fun `Cannot buy a lottery ticket with the amount less than one lottery ticket`() {
            val provider = LottoProvider(0)

            assertThat(provider.numberOfAutomaticLottos).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("If some are custom")
    inner class WithCustomLotto {
        @Test
        fun `The total # of lottery tickets equal to the sum of custom and quick pick tickets`() {
            val totalPayment = 5000

            val customLottoNumbers = listOf(
                LottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumbers(listOf(45, 44, 43, 42, 41, 40))
            )

            val lottoProvider = LottoProvider(totalPayment, customLottoNumbers)

            assertThat(lottoProvider.numberOfCustomLottos).isEqualTo(2)
            assertThat(lottoProvider.numberOfAutomaticLottos).isEqualTo(3)
            assertThat(lottoProvider.lottos.size).isEqualTo(5)
        }
        @Test
        fun `Custom lottery numbers are picked by a player`() {
            val totalPayment = 5000

            val customLottoNumbers = listOf(
                LottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumbers(listOf(45, 44, 43, 42, 41, 40))
            )

            val lottoProvider = LottoProvider(totalPayment, customLottoNumbers)

            assertThat(lottoProvider.lottos.map { it.numbers.list }).containsAll(customLottoNumbers.map { it.list })
        }

        @Test
        fun `IllegalArgumentException is thrown if more tickets are provided than the amount paid`() {
            val totalPayment = 1000

            val customLottoNumbers = listOf(
                LottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumbers(listOf(45, 44, 43, 42, 41, 40))
            )

            assertThatIllegalArgumentException().isThrownBy { LottoProvider(totalPayment, customLottoNumbers) }
        }

        @ParameterizedTest
        @CsvSource("1000, 0, true", "1000, 1, true", "1000, 2, false")
        fun `The result should tell whether requested number of tickets can be bought with the amount given`(payment: Int, numberOfLottoRequested: Int, result: Boolean) {
            assertThat(LottoProvider.isAffordable(payment, numberOfLottoRequested)).isEqualTo(result)
        }
    }
}
