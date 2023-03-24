import java.io.BufferedReader
import java.io.InputStreamReader

class Bj2116 {
    companion object {
        fun solve() {
            val br = BufferedReader(InputStreamReader(System.`in`))
            val n = br.readLine().toInt()
            val dices = Array(n) { listOf<Int>() }
            for (i in 0 until n) {
                dices[i] = br.readLine().split(" ").map { it.toInt() }
            }

            var ans = 0

            dices.first().mapIndexed{ index, it ->
                var downIndex: Int = index
                var max = 0
                var downValue = it
                dices.map { dice ->
                    downIndex = dice.indexOf(downValue)
                    val upIndex = getOpponent(downIndex)
                    max += dice.filterIndexed { index, i ->
                        index != downIndex && index != upIndex
                    }.max()
                    downValue = dice[upIndex]
                }
                ans = if (ans < max) max else ans
            }
            println(ans)
        }

        private fun getOpponent(up: Int) = when (up) {
            0 -> 5
            1 -> 3
            2 -> 4
            3 -> 1
            4 -> 2
            5 -> 0
            else -> {
                0
            }
        }
    }
}