import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bj12865 {

    public static void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            inputs = br.readLine().split(" ");
            int w = Integer.parseInt(inputs[0]);
            int v = Integer.parseInt(inputs[1]);

            for (int j = 1; j <= k; j++) {
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}