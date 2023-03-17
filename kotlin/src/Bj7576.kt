import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque

class Bj7576 {
    companion object{
        fun solve() {
            val direction = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))
            var br = BufferedReader(InputStreamReader(System.`in`))
            val (m, n) = br.readLine().split(" ").map { it.toInt() }
            val box = Array(n) { Array(m) { 0 } }
            for (i in 0..n - 1) {
                var st = StringTokenizer(br.readLine());
                for (j in 0..m - 1) {
                    box[i][j] = st.nextToken().toInt()
                }
            }
            val visited = Array(n) { Array(m) { false } }
            val queue = ArrayDeque<Pair<Int, Int>>()
            var ans = 0

            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (box[i][j] == 1 && !visited[i][j]) {
                        queue.add(Pair(i, j))
                    }
                }
            }
            while (queue.isNotEmpty()) {
                ans += 1
                val tmp = queue.map { it }
                queue.clear()
                for ((i, j) in tmp) {
                    direction.map {
                        val (dx, dy) = it
                        val x = i + dx
                        val y = j + dy
                        if (x in 0 until n) {
                            if (y in 0 until m) {
                                if (box[x][y] == 0) {
                                    box[x][y] = 1
                                    queue.add(Pair(x, y))
                                }
                            }
                        }
                    }
                }
            }

            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (box[i][j] == 0) {
                        println(-1)
                        return
                    }
                }
            }
            println(ans - 1)
        }
    }
}