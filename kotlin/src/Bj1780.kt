import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class Bj1780 {
    companion object{
        fun solve(){
            val br = BufferedReader(InputStreamReader(System.`in`))
            val n = br.readLine().toInt()
            val arr = Array(n) { Array(n) { 0 } }
            for (i in 0 until n) {
                val st = StringTokenizer(br.readLine())
                for (j in 0 until n) {
                    arr[i][j] = st.nextToken().toInt()
                }
            }
            val ans = arrayOf(0, 0, 0)
            cut(arr, ans, 0, 0, n)
            println(ans[0])
            println(ans[1])
            println(ans[2])
        }

        private fun cut(arr: Array<Array<Int>>, ans: Array<Int>, x: Int, y: Int, n: Int) {
            val index = arr[x][y]
            var flag = false
            for (i in x until x + n) {
                for (j in y until y + n) {
                    if (index != arr[i][j]) {
                        val cutting = n / 3
                        for (stepX in 0 until 3) {
                            for (stepY in 0 until 3) {
                                cut(arr, ans, x + stepX * cutting, y + stepY * cutting, cutting)
                            }
                        }
                        flag = true
                        break
                    }
                }
                if (flag) {
                    break
                }
            }
            if (!flag) {
                when (index) {
                    -1 -> ans[0] += 1
                    0 -> ans[1] += 1
                    1 -> ans[2] += 1
                }
            }
        }
    }
}