import java.io.BufferedReader
import java.io.InputStreamReader

class Bj2878 {
    companion object{
        fun solve(){
            val REMAIN = Math.pow(2.0, 64.0).toLong()
            val br = BufferedReader(InputStreamReader(System.`in`))
            val (m, n) = br.readLine().split(" ").map { it.toInt() }
            val friends = Array(n) { 0L }
            for (i in 0 until n) {
                friends[i] = br.readLine().toLong()
            }

            var remain = friends.sum() - m
            friends.sort()

            var angry = 0L
            friends.mapIndexed { index, candy ->
                friends[index] = Math.min(candy, remain / (friends.size - index))
                remain -= friends[index]
            }

            friends.map {
                angry += it * it
            }
            angry %= REMAIN
            println(angry)
        }
    }
}