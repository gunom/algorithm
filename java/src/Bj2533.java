import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][2];
        boolean[] visited = new boolean[n + 1];
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        dfs(1, tree, dp, visited);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int num, ArrayList<ArrayList<Integer>> tree, int[][] dp, boolean[] visited) {
        visited[num] = true;
        dp[num][0] = 0;
        dp[num][1] = 1;

        for (int child : tree.get(num)) {
            if (!visited[child]) {
                dfs(child, tree, dp, visited);
                dp[num][0] += dp[child][1];
                dp[num][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}

