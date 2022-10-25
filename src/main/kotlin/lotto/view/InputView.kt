package lotto.view

object InputView {
    private const val TOTAL_PAYMENT_QUESTION = "Enter the amount you plan to spend. (₩)"
    private const val NUMBER_OF_CUSTOM_LOTTO_QUESTION = "Enter the number of custom lottery tickets."
    private const val CUSTOM_LOTTO_NUMBERS_QUESTION = "Pick your lottery numbers."
    private const val WINNING_NUMBERS_QUESTION = "Enter the winning numbers."
    private const val BONUS_BALL_NUMBER_QUESTION = "Enter the bonus number."

    fun readTotalPayment(): Int {
        println(TOTAL_PAYMENT_QUESTION)

        return readln()
            .toPositiveNumber("The amount")
    }

    fun readNumberOfCustomLotto(): Int {
        println(NUMBER_OF_CUSTOM_LOTTO_QUESTION)

        return readln()
            .toPositiveNumber("The number of custom lottery tickets")
    }

    fun readCustomLottoNumbers(numberOfCustomLotto: Int): List<List<Int>> {
        if (numberOfCustomLotto > 0) {
            println(CUSTOM_LOTTO_NUMBERS_QUESTION)

            return (1..numberOfCustomLotto).map { readLottoNumbers("lottery numbers") }
        }

        return emptyList()
    }

    fun readWinningNumbers(): List<Int> {
        println("\n$WINNING_NUMBERS_QUESTION")

        return readLottoNumbers("The winning numbers")
    }

    fun readBonusBallNumber(): Int {
        println(BONUS_BALL_NUMBER_QUESTION)

        return readln().toPositiveNumber("A bonus number")
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
        ?: throw IllegalArgumentException("${what.toSubjectString()} must be a positive number greater than 0.")

    private fun List<Int>.takeIfIntListHasSizeOfSix(what: String? = null): List<Int> = takeIf { it.size == 6 }
        ?: throw IllegalArgumentException("${what.toSubjectString()} should have six numbers.")

    private fun String?.toSubjectString(): String = if (this == null) "" else "$this"
}
