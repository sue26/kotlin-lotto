package lotto.domain.prize

import lotto.domain.Lotto
import lotto.domain.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoPrizeResultTest {
    @Test
    fun `Total number of winning tickets`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        val noMatchingLotto = Lotto(listOf(45, 44, 43, 42, 41, 40))
        val oneMatchingLotto = Lotto(listOf(45, 44, 43, 42, 41, 1))
        val twoMatchingLotto = Lotto(listOf(45, 44, 43, 42, 2, 1))
        val threeMatchingLotto = Lotto(listOf(1, 2, 3, 45, 44, 43))
        val fourMatchingLotto = Lotto(listOf(1, 2, 3, 4, 45, 44))
        val fiveMatchingLotto1 = Lotto(listOf(1, 2, 3, 4, 5, 45))
        val fiveMatchingLotto2 = Lotto(listOf(1, 2, 3, 4, 5, 44))
        val sixMatchingLotto2 = Lotto(listOf(6, 5, 4, 3, 2, 1))

        val prizeResult = LottoPrizeResult(
            winningLotto,
            listOf(
                noMatchingLotto,
                oneMatchingLotto,
                twoMatchingLotto,
                threeMatchingLotto,
                fourMatchingLotto,
                fiveMatchingLotto1,
                fiveMatchingLotto2,
                sixMatchingLotto2
            )
        )

        val expectedPrizes = listOf(
            LottoPrize.MISS,
            LottoPrize.MISS,
            LottoPrize.MISS,
            LottoPrize.FIFTH,
            LottoPrize.FOURTH,
            LottoPrize.THIRD,
            LottoPrize.THIRD,
            LottoPrize.FIRST
        )

        assertThat(prizeResult.prizes).isEqualTo(expectedPrizes)
        assertThat(prizeResult.numberOf(LottoPrize.FIRST)).isEqualTo(1)
        assertThat(prizeResult.numberOf(LottoPrize.THIRD)).isEqualTo(2)
    }

    @Test
    fun `Total profit`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        val noMatchingLotto = Lotto(listOf(45, 44, 43, 42, 41, 40))
        val oneMatchingLotto = Lotto(listOf(45, 44, 43, 42, 41, 1))
        val twoMatchingLotto = Lotto(listOf(45, 44, 43, 42, 2, 1))
        val threeMatchingLotto = Lotto(listOf(1, 2, 3, 45, 44, 43))
        val fourMatchingLotto = Lotto(listOf(1, 2, 3, 4, 45, 44))
        val fiveMatchingLotto1 = Lotto(listOf(1, 2, 3, 4, 5, 45))
        val fiveMatchingLotto2 = Lotto(listOf(1, 2, 3, 4, 5, 44))
        val sixMatchingLotto2 = Lotto(listOf(6, 5, 4, 3, 2, 1))

        val prizeResult = LottoPrizeResult(
            winningLotto,
            listOf(
                noMatchingLotto,
                oneMatchingLotto,
                twoMatchingLotto,
                threeMatchingLotto,
                fourMatchingLotto,
                fiveMatchingLotto1,
                fiveMatchingLotto2,
                sixMatchingLotto2
            )
        )

        val expectedPrizes = listOf(
            LottoPrize.MISS,
            LottoPrize.MISS,
            LottoPrize.MISS,
            LottoPrize.FIFTH,
            LottoPrize.FOURTH,
            LottoPrize.THIRD,
            LottoPrize.THIRD,
            LottoPrize.FIRST
        )

        val expectedTotalPrize = expectedPrizes.sumOf { it.prizeAmount }

        assertThat(prizeResult.totalPrizeAmount).isEqualTo(expectedTotalPrize)
    }
}
