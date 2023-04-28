public class Pr92344 {
    // [[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5]]
    static final int[][] map = new int[][] {
        {5,5,5,5,5},
        {5,5,5,5,5},
        {5,5,5,5,5},
        {5,5,5,5,5}
    };
    //[[1,0,0,3,4,4],[1,2,0,2,3,2],[2,1,0,3,1,2],[1,0,1,3,3,1]]
    static final int[][] skill = new int[][] {
        {1,0,0,3,4,4},
        {1,2,0,2,3,2},
        {2,1,0,3,1,2},
        {1,0,1,3,3,1}
    };

    public static int solution(int[][] map, int[][] skill) {
        int answer = 0;
        int R = map.length;
        int C = map[0].length;
        int[][] mark = new int[R+1][C+1];

        for(int[] s: skill) {
            if(s[0]==2) {
                mark[s[1]][s[2]] += s[5];
                mark[s[1]][s[4]+1] -= s[5];
                mark[s[3]+1][s[2]] -= s[5];
                mark[s[3]+1][s[4]+1] += s[5];
            } else {
                mark[s[1]][s[2]] -= s[5];
                mark[s[1]][s[4]+1] += s[5];
                mark[s[3]+1][s[2]] += s[5];
                mark[s[3]+1][s[4]+1] -= s[5];
            }
        }

        for(int r=0; r<R+1; r++) {
            for(int c=0; c<C; c++) {
                mark[r][c+1] += mark[r][c];
            }
        }

        for(int c=0; c<C+1; c++) {
            for(int r=0; r<R; r++) {
                mark[r+1][c] += mark[r][c];
            }
        }

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(mark[r][c] + map[r][c] > 0)	++answer;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int answer = solution(map, skill);
        System.out.println(answer);
    }
}
