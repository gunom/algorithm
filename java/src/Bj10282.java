import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj10282 {
    static ArrayList<ArrayList<Node>> list;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int i =0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            distance = new int[n+1];
            for(int j = 0; j < n + 1; j++){
                list.add(new ArrayList<>());
            }

            for(int j = 0; j < d; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new Node(a, s));
            }

            Arrays.fill(distance, Integer.MAX_VALUE);

            dijkstra(c);

            int count = 0;
            int answer = 0;

            for(int j = 0; j < n + 1; j++){
                if(distance[j] != Integer.MAX_VALUE){
                    count++;
                    answer = Math.max(answer, distance[j]);
                }
            }

            System.out.println(count + " " + answer);
        }
    }

    static void dijkstra(int start){
        distance[start] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(start, 0));

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(distance[cur.index] < cur.distance) continue;

            for(Node next: list.get(cur.index)){
                int cost = distance[cur.index] + next.distance;
                if(cost < distance[next.index]){
                    distance[next.index] = cost;
                    queue.offer(new Node(next.index, cost));
                }
            }

        }
    }


    static class Node implements Comparable<Node>{

        int index;
        int distance;

        public Node(int index, int distance){
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if(this.distance < o.distance){
                return -1;
            }
            return 1;
        }
    }
}
