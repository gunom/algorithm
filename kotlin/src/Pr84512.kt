import java.io.BufferedReader
import java.io.InputStreamReader

class Pr84512 {
    companion object {
        fun solve(): Int {
            var br = BufferedReader(InputStreamReader(System.`in`))
            val word = br.readLine()
            return solution(word)
        }

        private fun solution(word: String): Int {
            val cWord = word.toCharArray()
            var cnt = word.length
            val multi = intArrayOf(781, 156, 31, 6, 1)
            var answer = 0
            while (cnt > 0) {
                answer += if (cWord[cWord.size - cnt] == 'A') {
                    multi[cWord.size - cnt] * 0 + 1
                } else if (cWord[cWord.size - cnt] == 'E') {
                    multi[cWord.size - cnt] * 1 + 1
                } else if (cWord[cWord.size - cnt] == 'I') {
                    multi[cWord.size - cnt] * 2 + 1
                } else if (cWord[cWord.size - cnt] == 'O') {
                    multi[cWord.size - cnt] * 3 + 1
                } else {
                    multi[cWord.size - cnt] * 4 + 1
                }
                cnt--
            }
            return answer
        }
    }
}