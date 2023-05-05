import java.util.HashMap;

public class Pr81301 {
    static String s = "one4seveneight";
    public static int solution(String s) {
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("zero", 0); map.put("one", 1); map.put("two", 2); map.put("three", 3); map.put("four", 4);
        map.put("five", 5); map.put("six", 6); map.put("seven", 7); map.put("eight", 8); map.put("nine", 9);
        String[] keys = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i = 0; i < keys.length; i++) {
            s = s.replaceAll(keys[i], map.get(keys[i]).toString());
        }
        answer = Integer.parseInt(s);
        return answer;
    }

    public static void main(String[] args) {
        int answer = solution(s);
        System.out.println(answer);
    }
}
