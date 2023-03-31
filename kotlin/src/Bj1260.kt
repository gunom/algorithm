import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class Bj1260 {
    companion object{
        fun solve(){
            val br = BufferedReader(InputStreamReader(System.`in`))
            val (n, m, v) = br.readLine().split(" ").map { it.toInt() }

            val graph = Array(n + 1) { mutableListOf<Int>() }
            repeat(m) {
                val (a, b) = br.readLine().split(" ").map { it.toInt() }
                graph[a].add(b)
                graph[b].add(a)
            }

            graph.forEach { it.sort() }

            val dfsResult = dfs(graph, BooleanArray(n + 1), v)
            println(dfsResult.joinToString(" "))

            val bfsResult = bfs(graph, BooleanArray(n + 1), v)
            println(bfsResult.joinToString(" "))
        }

        private fun dfs(graph: Array<out List<Int>>, visited: BooleanArray, v: Int): List<Int> {
            val result = mutableListOf<Int>()
            visited[v] = true
            result.add(v)
            for (next in graph[v]) {
                if (!visited[next]) {
                    result.addAll(dfs(graph, visited, next))
                }
            }
            return result
        }

        private fun bfs(graph: Array<out List<Int>>, visited: BooleanArray, v: Int): List<Int> {
            val result = mutableListOf<Int>()
            val queue: Queue<Int> = LinkedList()
            visited[v] = true
            queue.add(v)
            while (queue.isNotEmpty()) {
                val cur = queue.poll()
                result.add(cur)
                for (next in graph[cur]) {
                    if (!visited[next]) {
                        visited[next] = true
                        queue.add(next)
                    }
                }
            }
            return result
        }

    }
}