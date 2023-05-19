import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                board[i][j] = line.charAt(j) - '0';
                visited[i][j] = 0;
            }
        }
        List<Integer> answer = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 && visited[i][j] == 0) {
                    visited[i][j] = 1;
                    answer.add(bfs(i, j, board, visited));
                }
            }
        }
        answer = answer.stream().sorted().collect(Collectors.toList());
        System.out.println(answer.size());
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }

    private static int bfs(int x, int y, int[][] board, int[][] visited) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int cnt = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = position[0] + dx[i];
                int ny = position[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board.length || (nx == x
                    && ny == y)) {
                    continue;
                }
                if (board[nx][ny] == 1 && visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    queue.offer(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
