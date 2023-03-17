import java.io.BufferedReader
import java.io.InputStreamReader

//https://school.programmers.co.kr/learn/courses/30/lessons/60057
class PrCompressWord {
    companion object{
        fun solve(){
            var br = BufferedReader(InputStreamReader(System.`in`))
            val s = br.readLine()
            fun solution(s: String): Int {
                val str = s.toList()
                val wordList = (1 until str.size).map { length ->
                    (0 until str.size step length).map { idx ->
                        if (idx + length < str.size) {
                            str.subList(idx, idx + length).joinToString("")
                        } else {
                            str.subList(idx, str.size).joinToString("")
                        }
                    }
                }
                var ans = if (s.length == 1) 1 else Int.MAX_VALUE

                wordList.map { word ->
                    var temp = 0
                    var cnt = 1
                    for (i in 0..word.size - 2) {
                        if (word[i] != word[i + 1]) {
                            temp += if (cnt == 1) word[i].length else word[i].length + cnt.toString().length
                            cnt = 1
                        } else {
                            cnt += 1
                        }
                        if (i == word.size - 2) {
                            temp += if (cnt == 1) word[i + 1].length else word[i + 1].length + cnt.toString().length
                        }
                    }
                    ans = Math.min(ans, temp)
                }
                return (ans)
            }
            println(solution(s))
        }
    }
}