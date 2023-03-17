import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class Bj14889 {
    companion object {
        private var ans = Integer.MAX_VALUE;
        private var br = BufferedReader(InputStreamReader(System.`in`))
        private val N = br.readLine().toInt()
        private var arr = Array(N) { Array(N) { 0 } }
        private var visit = Array<Boolean>(N, { false })

        fun solve() {
            for (i in 0..N - 1) {
                var st = StringTokenizer(br.readLine());
                for (j in 0..N - 1) {
                    arr[i][j] = st.nextToken().toInt()
                }
            }
            solution(0, 0)
            println(ans)
        }

        private fun solution(start: Int, depth: Int) {
            if (depth == N / 2) {
                diff()
                return
            }
            for (i in start..N - 1) {
                if (visit[i]) continue
                visit[i] = true
                solution(i + 1, depth + 1)
                visit[i] = false
            }
        }


        private fun diff() {
            var scoreA = 0
            var scoreB = 0
            for (i in 0..N - 2) {
                for (j in i + 1..N - 1) {
                    if (visit[i] && visit[j]) {
                        scoreA += arr[i][j] + arr[j][i]
                    } else if (!visit[i] && !visit[j]) {
                        scoreB += arr[i][j] + arr[j][i]
                    }
                }
            }
            val dif = Math.abs(scoreA - scoreB)
            ans = Math.min(ans, dif)
            if (ans == 0) {
                println(ans)
                System.exit(0);
            }
        }
    }
}