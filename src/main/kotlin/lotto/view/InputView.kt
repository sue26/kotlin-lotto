package lotto.view

object InputView {
    private const val TOTAL_PAYMENT_QUESTION = "구입금액을 입력해 주세요."
    private const val NUMBER_OF_CUSTOM_LOTTO_QUESTION = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val CUSTOM_LOTTO_NUMBERS_QUESTION = "수동으로 구매할 번호를 입력해 주세요."
    private const val WINNING_NUMBERS_QUESTION = "지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_BALL_NUMBER_QUESTION = "보너스 볼을 입력해 주세요."

    fun readTotalPayment(): Int {
        println(TOTAL_PAYMENT_QUESTION)

        return readln()
            .toPositiveNumber("구입금액")
    }

    fun readNumberOfCustomLotto(): Int {
        println(NUMBER_OF_CUSTOM_LOTTO_QUESTION)

        return readln()
            .toPositiveNumber("수동으로 구매할 로또 수")
    }

    fun readCustomLottoNumbers(numberOfCustomLotto: Int): List<List<Int>> {
        if (numberOfCustomLotto > 0) {
            println(CUSTOM_LOTTO_NUMBERS_QUESTION)

            return (1..numberOfCustomLotto).map { readLottoNumbers("로또 번호") }
        }

        return emptyList()
    }

    fun readWinningNumbers(): List<Int> {
        println("\n$WINNING_NUMBERS_QUESTION")

        return readLottoNumbers("당첨 번호")
    }

    fun readBonusBallNumber(): Int {
        println(BONUS_BALL_NUMBER_QUESTION)

        return readln().toPositiveNumber("보너스 볼 번호")
    }

    private fun readLottoNumbers(what: String? = null): List<Int> {
        return readln()
            .split(",")
            .map { it.toPositiveNumber(what) }
            .takeIfIntListHasSizeOfSix(what)
    }

    private fun String.toPositiveNumber(what: String? = null): Int = trim()
        .toIntOrNull()
        ?.takeIf { it >= 0 }
        ?: throw IllegalArgumentException("${what.toSubjectString()}0 이상의 정수이여야 합니다.")

    private fun List<Int>.takeIfIntListHasSizeOfSix(what: String? = null): List<Int> = takeIf { it.size == 6 }
        ?: throw IllegalArgumentException("${what.toSubjectString()}총 6개의 숫자로 이루어져야 합니다.")

    private fun String?.toSubjectString(): String = if (this == null) "" else "${this}은(는) "
}
