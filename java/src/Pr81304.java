import java.util.*;

public class Pr81304 {
    static int n = 3;
    static int start = 1;
    static int end = 3;
    static int[][] roads = {{1, 2, 2}, {3, 2, 3}};
    static int[] traps = {2};

    public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;

        Map<Integer, List<Integer>> roadsFrom = new HashMap<>();
        Map<Integer, List<Integer>> roadsTo = new HashMap<>();
        Map<Integer, Map<List<Integer>, Integer>> visited = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            visited.put(i, new HashMap<>());
            roadsFrom.put(i, new ArrayList<>());
            roadsTo.put(i, new ArrayList<>());
        }

        for (int[] road : roads) {
            int s = road[0];
            int e = road[1];
            int c = road[2];
            roadsFrom.get(s).add(e);
            roadsTo.get(e).add(s);
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[]{0, start, 0});

        visited.get(start).put(new ArrayList<>(), 0);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int count = cur[0];
            int curPos = cur[1];
            int trapMask = cur[2];

            if (curPos == end) {
                return count;
            }

            List<Integer> trapsOn = new ArrayList<>();
            for (int i = 0; i < traps.length; i++) {
                int mask = 1 << i;
                if ((trapMask & mask) != 0) {
                    trapsOn.add(traps[i]);
                }
            }

            if (!trapsOn.contains(curPos)) {
                for (int next : roadsFrom.get(curPos)) {
                    if (!trapsOn.contains(next)) {
                        push(q, visited, count, trapsOn, next, 0);
                    }
                }
                for (int prev : roadsTo.get(curPos)) {
                    if (trapsOn.contains(prev)) {
                        push(q, visited, count, trapsOn, prev, 0);
                    }
                }
            } else {
                for (int next : roadsFrom.get(curPos)) {
                    if (trapsOn.contains(next)) {
                        push(q, visited, count, trapsOn, next, trapMask);
                    }
                }
                for (int prev : roadsTo.get(curPos)) {
                    if (!trapsOn.contains(prev)) {
                        push(q, visited, count, trapsOn, prev, trapMask);
                    }
                }
            }
        }

        return answer;
    }

    private static void push(
        PriorityQueue<int[]> q, Map<Integer, Map<List<Integer>, Integer>> visited,
        int count, List<Integer> trapsOn, int next, int trapMask
    ) {
        List<Integer> newTrapsOn = renewTraps(trapsOn, next);
        if (visited.containsKey(next) && visited.get(next).containsKey(newTrapsOn)
            && visited.get(next).get(newTrapsOn) <= count + 1) {
            return;
        }
        visited.computeIfAbsent(next, k -> new HashMap<>()).put(newTrapsOn, count + 1);
        q.offer(new int[]{count + 1, next, trapMask(newTrapsOn)});
    }

    private static List<Integer> renewTraps(List<Integer> trapsOn, int newTrap) {
        if (!trapsOn.contains(newTrap)) {
            List<Integer> newTraps = new ArrayList<>(trapsOn);
            newTraps.add(newTrap);
            return newTraps;
        } else {
            List<Integer> newTraps = new ArrayList<>(trapsOn);
            newTraps.remove(Integer.valueOf(newTrap));
            return newTraps;
        }
    }

    private static int trapMask(List<Integer> trapsOn) {
        int mask = 0;
        for (int i = 0; i < trapsOn.size(); i++) {
            if ((1 << i) == 0) continue;
            if (trapsOn.contains(trapsOn.get(i))) {
                mask |= 1 << i;
            }
        }
        return mask;
    }

    public static void main(String[] args) {
        int answer = solution(n, start, end, roads, traps);
        System.out.println(answer);
    }
}