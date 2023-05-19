import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bj13023 {
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < N; i++){
            list.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < M; i++ ){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list.get(from).add(to);
            list.get(to).add(from);
        }

        int answer = 0;
        for(int i = 0; i < N; i++){
            if(answer == 1) break;
            visited = new boolean[N];
            visited[i] = true;
            answer = dfs(i, 1);
        }
        System.out.println(answer);
    }

    static int dfs(int cur, int count){
        if(count == 5){
            return 1;
        }
        for(int next: list.get(cur)){
            if(!visited[next]){
                visited[next] = true;
                if(dfs(next, count + 1)==1){
                    return 1;
                } else {
                    visited[next] = false;
                }
            }
        }
        return 0;
    }
}
