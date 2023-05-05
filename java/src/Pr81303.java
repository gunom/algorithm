import java.util.Arrays;
import java.util.Stack;

public class Pr81303 {

    static int n = 8;
    static int k = 2;

    static String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};

    static class Node {

        int num;
        int pre;
        int next;

        Node(int num, int pre, int next) {
            this.num = num;
            this.pre = pre;
            this.next = next;
        }
    }

    public static String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder("O".repeat(n));
        String answer = "";
        int[] pre = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i - 1;
            next[i] = i + 1;
        }

        next[n - 1] = -1;
        Stack<Node> stack = new Stack<>();
        for (String inst : cmd) {
            String[] operation = inst.split(" ");
            if (operation[0].equals("D")) {
                int cnt = Integer.parseInt(operation[1]);
                while (cnt-- > 0) {
                    k = next[k];
                }
            } else if (operation[0].equals("U")) {
                int cnt = Integer.parseInt(operation[1]);
                while (cnt-- > 0) {
                    k = pre[k];
                }
            } else if (operation[0].equals("C")) {
                stack.push(new Node(k, pre[k], next[k]));
                sb.setCharAt(k, 'X');
                if (next[k] == -1) {
                    next[pre[k]] = -1;
                } else if (pre[k] == -1) {
                    pre[next[k]] = -1;
                } else {
                    next[pre[k]] = next[k];
                    pre[next[k]] = pre[k];
                }
                if (next[k] == -1) {
                    k = pre[k];
                } else {
                    k = next[k];
                }
            } else if (operation[0].equals("Z")) {
                Node ret = stack.pop();
                sb.setCharAt(ret.num, 'O');
                if (ret.next == -1) {
                    next[ret.pre] = ret.num;
                } else if (ret.pre == -1) {
                    pre[ret.next] = ret.num;
                } else {
                    next[ret.pre] = ret.num;
                    pre[ret.next] = ret.num;
                }
            }
        }
        answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        String answer = solution(n, k, cmd);
        System.out.println(answer);
    }
}
