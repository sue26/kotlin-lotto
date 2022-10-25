package lotto.domain

import lotto.domain.numbers.LottoNumber
import lotto.domain.numbers.LottoNumbers
import lotto.domain.prize.LottoPrize

class WinningLotto(winningNumbers: List<Int>, bonusNumber: Int) {
    val winningNumbers = LottoNumbers(winningNumbers)
    val bonusNumber = LottoNumber.from(bonusNumber)

    init {
        require(!winningNumbers.contains(bonusNumber)) { "A bonus number cannot be a winning number." }
    }

    fun getLottoPrizeOf(lotto: Lotto): LottoPrize = LottoPrize.of(getNumberOfMatchingNumbers(lotto), matchesBonus(lotto))

    fun getNumberOfMatchingNumbers(lotto: Lotto): Int = winningNumbers.list.intersect(lotto.numbers.list.toSet()).size

    fun matchesBonus(lotto: Lotto): Boolean = lotto.numbers.list.contains(bonusNumber)
}
