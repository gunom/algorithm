import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrCompressWord {
    public static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        List<Character> str = new ArrayList<>();
        for (char c : s.toCharArray()) {
            str.add(c);
        }
        List<List<String>> wordList = new ArrayList<>();
        for (int length = 1; length < str.size(); length++) {
            List<String> list = new ArrayList<>();
            for (int idx = 0; idx < str.size(); idx += length) {
                if (idx + length < str.size()) {
                    list.add(s.substring(idx, idx + length));
                } else {
                    list.add(s.substring(idx));
                }
            }
            wordList.add(list);
        }
        int ans = s.length() == 1 ? 1 : Integer.MAX_VALUE;
        for (List<String> word : wordList) {
            int temp = 0;
            int cnt = 1;
            for (int i = 0; i < word.size() - 1; i++) {
                if (!word.get(i).equals(word.get(i + 1))) {
                    temp += cnt == 1 ? word.get(i).length() : word.get(i).length() + Integer.toString(cnt).length();
                    cnt = 1;
                } else {
                    cnt++;
                }
                if (i == word.size() - 2) {
                    temp += cnt == 1 ? word.get(i + 1).length() : word.get(i + 1).length() + Integer.toString(cnt).length();
                }
            }
            ans = Math.min(ans, temp);
        }
        return ans;
    }
}
