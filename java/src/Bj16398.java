import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj16398 {

    static ArrayList<ArrayList<Node>> list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                list.get(n).add(new Node(j, cost));
            }
        }

        long answer = 0;

        answer = prim(N);

        System.out.println(answer);
    }

    static long prim(int n) {
        long result = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0));
        int count = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visited[cur.index]) {
                continue;
            }
            visited[cur.index] = true;
            result += cur.cost;
            for (Node next : list.get(cur.index)) {
                if (!visited[next.index]) {
                    queue.add(next);
                }
            }

            if (++count == n) {
                break;
            }
        }

        return result;
    }

    static class Node implements Comparable<Node> {

        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) {
                return -1;
            }
            return 1;
        }
    }
}
