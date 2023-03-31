import java.util.*;

public class Bj1260 {
    public static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int v = sc.nextInt();

        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }

        List<Integer> dfsResult = dfs(graph, new boolean[n + 1], v);
        System.out.println(dfsResult.toString().replaceAll("[\\[\\],]", ""));

        List<Integer> bfsResult = bfs(graph, new boolean[n + 1], v);
        System.out.println(bfsResult.toString().replaceAll("[\\[\\],]", ""));
    }

    private static List<Integer> dfs(List<Integer>[] graph, boolean[] visited, int v) {
        List<Integer> result = new ArrayList<>();
        visited[v] = true;
        result.add(v);
        for (int next : graph[v]) {
            if (!visited[next]) {
                result.addAll(dfs(graph, visited, next));
            }
        }
        return result;
    }

    private static List<Integer> bfs(List<Integer>[] graph, boolean[] visited, int v) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return result;
    }
}


