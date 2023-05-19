import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pr92334 {

    static final String[] id_list = new String[]{
        "con", "ryan"
    };
    static final String[] report = new String[]{
    "ryan con", "ryan con", "ryan con", "ryan con"
    };

    static final int k = 3;

    public static int[] solution(String[] id_list, String[] reports, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, List<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> reportCountMap = new HashMap<>();
        for (String report : reports) {
            String[] reportPair = report.split(" ");
            List<String> reportList = reportMap.getOrDefault(reportPair[0], new ArrayList<>());
            if(reportList.contains(reportPair[1])) continue;
            reportList.add(reportPair[1]);
            reportMap.put(reportPair[0], reportList);
            reportCountMap.put(reportPair[1], reportCountMap.getOrDefault(reportPair[1], 0) + 1);
        }
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            int count = 0;
            List<String> reportList = reportMap.getOrDefault(id, new ArrayList<>());
            for (String reportId : reportList) {
                if (reportCountMap.get(reportId) >= k) {
                    count++;
                }
            }
            answer[i] = count;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution(id_list, report, k);
        for (int i : answer) {
            System.out.println(i);
        }
    }
}
