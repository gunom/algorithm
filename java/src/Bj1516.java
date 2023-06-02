import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] time = new int[n + 1];
        int[] indegree = new int[n + 1];
        ArrayList<ArrayList<Integer>> preBuild = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            preBuild.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i + 1] = t;
            while (true) {
                int pre = Integer.parseInt(st.nextToken());
                if (pre == -1) {
                    break;
                }
                preBuild.get(pre).add(i + 1);
                indegree[i + 1]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] result = new int[n + 1];

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : preBuild.get(cur)) {
                result[next] = Math.max(result[next], result[cur] + time[cur]);
                indegree[next]--;

                if(indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(time[i] + result[i]);
        }

    }
}
