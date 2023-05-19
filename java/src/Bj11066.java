import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj11066 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test = 0; test < t; test++) {
            int k = Integer.parseInt(br.readLine());
            int[] page = new int[k + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                page[i + 1] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[k + 1][k + 1];
            int[] sum = new int[k + 1];
            for (int i = 1; i <= k; i++) {
                sum[i] = sum[i - 1] + page[i];
            }

            for (int i = 1; i <= k; i++) {
                for (int start = 1; start <= k - i; start++) {
                    int end = start + i;
                    dp[start][end] = Integer.MAX_VALUE;
                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(
                            dp[start][end],
                            dp[start][mid] + dp[mid + 1][end] + sum[end] - sum[start - 1]
                        );
                    }
                }
            }

            System.out.println(dp[1][k]);
        }
    }
}
