import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj15486 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] schedule = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            schedule[i][0] = Integer.parseInt(input[0]);
            schedule[i][1] = Integer.parseInt(input[1]);
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++){
            dp[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            if (i + schedule[i][0] <= n) {
                dp[i + schedule[i][0]] = Math.max(dp[i + schedule[i][0]], dp[i] + schedule[i][1]);
            }
        }

        System.out.println(dp[n]);
    }
}
