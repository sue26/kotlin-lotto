package lotto.domain

import lotto.domain.numbers.LottoNumber
import lotto.domain.prize.LottoPrize
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `The winning numbers are entered by the player`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val winningLotto = WinningLotto(winningNumbers, 7)

        assertThat(winningLotto.winningNumbers.list).isEqualTo(winningNumbers.map { LottoNumber.from(it) })
    }

    @Test
    fun `A bonus number is stored with the winning numbers`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 7
        val winningLotto = WinningLotto(winningNumbers, bonusBallNumber)

        assertThat(winningLotto.bonusNumber).isEqualTo(LottoNumber.from(bonusBallNumber))
    }

    @Test
    fun `A bonus number should not equal to any winning number, else IllegalArgumentsException is thrown`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 10

        assertThatIllegalArgumentException().isThrownBy { WinningLotto(winningNumbers, bonusBallNumber) }
    }

    @Test
    fun `Can check whether any number matched the bonus number`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 1
        val winningLotto = WinningLotto(winningNumbers, bonusBallNumber)

        val lottoWithBonusNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val lottoWithoutBonusNumber = Lotto(listOf(2, 3, 4, 5, 6, 7))

        assertTrue(winningLotto.matchesBonus(lottoWithBonusNumber))
        assertFalse(winningLotto.matchesBonus(lottoWithoutBonusNumber))
    }

    @Test
    fun `Can check the total number of matched numbers`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val winningLotto = WinningLotto(winningNumbers, 7)

        val threeMatchingNumbers = listOf(10, 20, 30, 1, 2, 3)
        val candidateLotto1 = Lotto(threeMatchingNumbers)

        assertThat(winningLotto.getNumberOfMatchingNumbers(candidateLotto1)).isEqualTo(3)
    }

    @Test
    fun `Can check the total number of matched numbers (different order)`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val winningLotto = WinningLotto(winningNumbers, 7)

        val sixMatchingNumbers = listOf(40, 35, 10, 45, 20, 30)
        val candidateLotto2 = Lotto(sixMatchingNumbers)

        assertThat(winningLotto.getNumberOfMatchingNumbers(candidateLotto2)).isEqualTo(6)
    }

    @Test
    fun `LottoPrize is calculated based on the number of matching numbers`() {
        val winningNumbers = listOf(10, 20, 30, 35, 40, 45)
        val bonusBallNumber = 1
        val winningLotto = WinningLotto(winningNumbers, bonusBallNumber)

        val lottoWithSecondPrize = Lotto(listOf(10, 20, 30, 35, 40, 1))

        assertThat(winningLotto.getLottoPrizeOf(lottoWithSecondPrize)).isEqualTo(LottoPrize.SECOND)
    }
}
