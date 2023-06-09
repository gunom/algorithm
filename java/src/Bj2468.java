import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj2468 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map;
        int n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        int maxHeight = 1;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }
        int max = 0;

        for(int h = 0; h <= maxHeight; h++){
            int count = 0;
            boolean[][] visited = new boolean[n][n];
            Queue<int[]> q = new LinkedList<>();
            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!visited[i][j] && map[i][j] > h){
                        q.add(new int[]{i, j});

                        visited[i][j] = true;
                        while (!q.isEmpty()){
                            int[] cur = q.poll();
                            for(int k = 0; k < 4; k++){
                                int nx = cur[0] + dx[k];
                                int ny = cur[1] + dy[k];
                                if(nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && map[nx][ny] > h){
                                    q.add(new int[]{nx, ny});
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                        count++;
                    }
                }
            }
            max = Math.max(max, count);
        }

        System.out.println(max);
    }
}
