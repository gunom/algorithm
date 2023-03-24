import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bj2116 {
    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] dices = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            String[] diceLine = br.readLine().split(" ");
            dices[i] = new ArrayList<>();
            for (String value : diceLine) {
                dices[i].add(Integer.parseInt(value));
            }
        }

        int ans = 0;

        for (int i = 0; i < 6; i++) {
            int downIndex = i;
            int max = 0;
            int downValue = dices[0].get(i);
            for (List<Integer> dice : dices) {
                downIndex = dice.indexOf(downValue);
                int upIndex = getOpponent(downIndex);
                int finalDownValue = downValue;
                max += dice.stream()
                    .filter((value) -> value != finalDownValue && dice.indexOf(value) != upIndex)
                    .max(Integer::compare).get();
                downValue = dice.get(upIndex);
            }
            ans = Math.max(ans, max);
        }

        System.out.println(ans);
    }

    private static int getOpponent(int up) {
        switch (up) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 0;
            default:
                return 0;
        }
    }
}
