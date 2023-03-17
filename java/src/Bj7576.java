import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj7576 {
    static void solve() {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input = br.readLine().split(" ");
            int m = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);

            int[][] box = new int[n][m];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    box[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean[][] visited = new boolean[n][m];
            Queue<int[]> queue = new LinkedList<>();
            int ans = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (box[i][j] == 1 && !visited[i][j]) {
                        queue.add(new int[]{i, j});
                    }
                }
            }

            while (!queue.isEmpty()) {
                ans++;
                Queue<int[]> tmp = new LinkedList<>(queue);
                queue.clear();
                while (!tmp.isEmpty()) {
                    int[] cell = tmp.remove();
                    int i = cell[0];
                    int j = cell[1];
                    for (int[] dir : direction) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < n && y >= 0 && y < m && box[x][y] == 0 && !visited[x][y]) {
                            box[x][y] = 1;
                            visited[x][y] = true;
                            queue.add(new int[]{x, y});
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (box[i][j] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
            System.out.println(ans - 1);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
