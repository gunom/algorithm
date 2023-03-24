import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1780 {
    static void solve(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[] ans = {0, 0, 0};
            cut(arr, ans, 0, 0, n);
            System.out.println(ans[0]);
            System.out.println(ans[1]);
            System.out.println(ans[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cut(int[][] arr, int[] ans, int x, int y, int n) {
        int index = arr[x][y];
        boolean flag = false;
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (index != arr[i][j]) {
                    int cutting = n / 3;
                    for (int stepX = 0; stepX < 3; stepX++) {
                        for (int stepY = 0; stepY < 3; stepY++) {
                            cut(arr, ans, x + stepX * cutting, y + stepY * cutting, cutting);
                        }
                    }
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        if (!flag) {
            switch (index) {
                case -1:
                    ans[0] += 1;
                    break;
                case 0:
                    ans[1] += 1;
                    break;
                case 1:
                    ans[2] += 1;
                    break;
            }
        }
    }
}
