import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj2878 {
    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long REMAIN = (long) Math.pow(2, 64);
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long[] friends = new long[n];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            friends[i] = num;
            sum += num;
        }

        long remain = sum - m;
        Arrays.sort(friends);

        long angry = 0;
        for (int i = 0; i < n; i++) {
            long removeCount = Math.min(friends[i], remain / (n - i));
            remain -= removeCount;
            friends[i] = removeCount;
            angry += removeCount * removeCount;
            angry %= REMAIN;
        }

        System.out.println(angry);
    }
}
