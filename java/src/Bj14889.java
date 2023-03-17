import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj14889 {
    private static int[][] arr;
    private static boolean[] visit;
    private static int N = 0;
    private static int ans = Integer.MAX_VALUE;

    public static void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visit = new boolean[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solution(0, 0);
            System.out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void solution(int start, int depth) {
        if (depth == N / 2) {
            diff();
            return;
        }
        for (int i = start; i < N; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            solution(i + 1, depth + 1);
            visit[i] = false;
        }
    }

    private static void diff() {
        int scoreA = 0;
        int scoreB = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visit[i] && visit[j]) {
                    scoreA += arr[i][j] + arr[j][i];
                } else if (!visit[i] && !visit[j]) {
                    scoreB += arr[i][j] + arr[j][i];
                }
            }
        }
        int dif = Math.abs(scoreA - scoreB);
        ans = Math.min(ans, dif);
        if (ans == 0) {
            System.out.println(ans);
            System.exit(0);
        }
    }
}
