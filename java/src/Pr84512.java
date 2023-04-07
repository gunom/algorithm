import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr84512 {
    public static int solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        return solution(word);
    }

    private static int solution(String word) {
        char[] cWord = word.toCharArray();
        int cnt = word.length();
        int[] multi = { 781, 156, 31, 6, 1 };
        int answer = 0;
        while (cnt > 0) {
            if (cWord[cWord.length - cnt] == 'A') {
                answer += multi[cWord.length - cnt] * 0 + 1;
            } else if (cWord[cWord.length - cnt] == 'E') {
                answer += multi[cWord.length - cnt] * 1 + 1;
            } else if (cWord[cWord.length - cnt] == 'I') {
                answer += multi[cWord.length - cnt] * 2 + 1;
            } else if (cWord[cWord.length - cnt] == 'O') {
                answer += multi[cWord.length - cnt] * 3 + 1;
            } else {
                answer += multi[cWord.length - cnt] * 4 + 1;
            }
            cnt--;
        }
        return answer;
    }
}
