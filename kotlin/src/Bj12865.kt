import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

class Bj12865 {
    companion object{
        fun solve(){
            val br = BufferedReader(InputStreamReader(System.`in`))
            val (n, k) = br.readLine().split(" ").map { it.toInt() }

            val dp = Array(n + 1) { IntArray(k + 1) }

            for (i in 1..n) {
                val (w, v) = br.readLine().split(" ").map { it.toInt() }

                for (j in 1..k) {
                    if (j >= w) {
                        dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - w] + v)
                    } else {
                        dp[i][j] = dp[i - 1][j]
                    }
                }
            }

            println(dp[n][k])
        }
    }
}