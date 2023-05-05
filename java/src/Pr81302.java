import java.util.LinkedList;
import java.util.Queue;

public class Pr81302 {

    static String[][] places = new String[][]{
        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
    };

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i = 0; i < places.length; i++){
            String[] place = places[i];
            boolean flag = true;
            for(int r = 0; r < 5 && flag; r++){
                for(int c=0; c< 5 && flag; c++){
                    if(place[r].charAt(c) == 'P'){
                        if(!bfs(r, c, place)){
                            flag = false;
                        }
                    }
                }
            }
            answer[i] = flag ? 1 : 0;
        }
        return answer;
    }

    private static boolean bfs(int r, int c, String[] p) {
        int dr[] = { -1, 1, 0, 0 };
        int dc[] = { 0, 0, -1, 1 };

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] { r, c });

        while (!queue.isEmpty()) {
            int[] position = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = position[0] + dr[i];
                int nc = position[1] + dc[i];

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || (nr == r && nc == c))
                    continue;

                int d = Math.abs(nr - r) + Math.abs(nc - c);

                if (p[nr].charAt(nc) == 'P' && d <= 2)
                    return false;
                else if (p[nr].charAt(nc) == 'O' && d < 2)
                    queue.offer(new int[] { nr, nc });
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] answer = solution(places);
        System.out.println(answer);
    }
}
