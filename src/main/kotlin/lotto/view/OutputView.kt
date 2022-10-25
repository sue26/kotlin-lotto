package lotto.view

import lotto.domain.Lotto
import lotto.domain.prize.LottoPrize
import lotto.domain.prize.LottoPrizeResult

object OutputView {
    fun printNumberOfLottosBought(customLottoNumber: Int, automaticLottoNumber: Int) {
        println("Custom: ${customLottoNumber}, Quick Pick: $automaticLottoNumber")
    }

    fun printLottoNumbers(lottos: List<Lotto>) {
        lottos.forEach { println(it.numbers.list.map { lottoNumber -> lottoNumber.number }) }
    }

    fun printLottoPrizeStatistics(payment: Int, prizeResult: LottoPrizeResult) {
        println()
        println("Winning Statistics")
        println("---------")
        printHitLottoPrizes(prizeResult)
        printProfitRate(payment, prizeResult.totalPrizeAmount)
    }

    private fun printHitLottoPrizes(prizeResult: LottoPrizeResult) {
        listOf(LottoPrize.FIFTH, LottoPrize.FOURTH, LottoPrize.THIRD, LottoPrize.SECOND, LottoPrize.FIRST)
            .forEach { it.printStatistics(prizeResult.numberOf(it)) }
    }

    private fun LottoPrize.printStatistics(totalNumber: Int) {
        val bonusMatchString = when (matchesBonus) {
            true -> ", and a bonus number"
            false -> ""
        }
        println("Matched $numberOfMatches winning numbers$bonusMatchString (${prizeAmount}₩)- $totalNumber lottery tickets")
    }

    private fun printProfitRate(payment: Int, totalPrizeAmount: Int) {
        val profitRate = totalPrizeAmount.toFloat() / payment
        val profitString = when {
            profitRate < 1 -> "lost"
            else -> "won"
        }
        println("Profitability: ${String.format("%.2f", profitRate)} (You ${profitString} money)")
    }
}
