package lotto

fun main() {
    val amountOfMoney = InputView.inputAmountOfMoney()
    val amountOfLotto = LottoProgram.getAmountOfLotto(amountOfMoney)
    val lottos = Lottos(amountOfLotto)
    OutputView.showUserLottos(lottos.getLottos())
    val numbers = InputView.inputWinningNumbers()
    val bonusBall = InputView.inputBonusBall()
    val winningNumbers = WinningNumbers.getInstance(numbers)
    val matchingRanks = lottos.matchLottos(winningNumbers, bonusBall)
    val rateOfReturn = LottoProgram.calculateRateOfReturn(matchingRanks, amountOfMoney)
    OutputView.showResults(matchingRanks, rateOfReturn)
}
