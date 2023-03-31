import java.io.BufferedReader
import java.io.InputStreamReader

class Bj1149 {
    companion object{
        fun solve(){
            val br = BufferedReader(InputStreamReader(System.`in`))
            val n = br.readLine().toInt()
            val cost = Array(n) { IntArray(3) }
            for (i in 0 until n) {
                val inputs = br.readLine().split(" ").map { it.toInt() }
                for (j in 0..2) {
                    cost[i][j] = inputs[j]
                }
            }
            val dp = Array(n) { IntArray(3) }
            dp[0][0] = cost[0][0]
            dp[0][1] = cost[0][1]
            dp[0][2] = cost[0][2]
            for (i in 1 until n) {
                dp[i][0] = cost[i][0] + minOf(dp[i - 1][1], dp[i - 1][2])
                dp[i][1] = cost[i][1] + minOf(dp[i - 1][0], dp[i - 1][2])
                dp[i][2] = cost[i][2] + minOf(dp[i - 1][0], dp[i - 1][1])
            }
            println(dp[n - 1].minOrNull())
        }
    }
}