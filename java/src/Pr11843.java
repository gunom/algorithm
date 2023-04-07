import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr11843 {
    public int solution(String[] arr) {
        int operandsCount = (int) Math.ceil(arr.length / 2.0);
        int[][] max_dp = new int[operandsCount][operandsCount];
        int[][] min_dp = new int[operandsCount][operandsCount];
        for (int i = 0; i < operandsCount; i++) {
            Arrays.fill(max_dp[i], Integer.MIN_VALUE);
            Arrays.fill(min_dp[i], Integer.MAX_VALUE);
        }
        for(int i = 0; i < operandsCount; i++) {
            max_dp[i][i] = Integer.parseInt(arr[i*2]);
            min_dp[i][i] = Integer.parseInt(arr[i*2]);
        }

        for(int cnt = 1; cnt < operandsCount; cnt++) {
            for(int i = 0; i < operandsCount - cnt; i++) {
                int j = i + cnt;
                for(int k = i; k < j; k++) {
                    if (arr[k*2+1].equals("+")) {
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k] + max_dp[k+1][j]);
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k] + min_dp[k+1][j]);
                    } else {
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k] - min_dp[k+1][j]);
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k] - max_dp[k+1][j]);
                    }
                }
            }
        }

        return max_dp[0][operandsCount-1];
    }
}
