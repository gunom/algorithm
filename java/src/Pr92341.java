import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> carHashMap = new HashMap<String, Integer>();
        HashMap<String, Integer> timeHashMap = new HashMap<String, Integer>();
        HashMap<String, Integer> priceHashMap = new HashMap<String, Integer>();
        for (String s : records) {
            String[] record = s.split(" ");
            String time = record[0];
            String carNumber = record[1];
            String inOut = record[2];
            if (inOut.equals("IN")) {
                carHashMap.put(carNumber, getTime(time));
            } else {
                int inTime = carHashMap.get(carNumber);
                int outTime = getTime(time);
                int parkingTime = outTime - inTime;
                timeHashMap.put(carNumber, timeHashMap.getOrDefault(carNumber, 0) + parkingTime);
                carHashMap.remove(carNumber);
            }
        }

        for (String carNumber : carHashMap.keySet()) {
            int inTime = carHashMap.get(carNumber);
            int outTime = 23 * 60 + 59;
            int parkingTime = outTime - inTime;
            timeHashMap.put(carNumber, timeHashMap.getOrDefault(carNumber, 0) + parkingTime);
        }

        for (String carNumber : timeHashMap.keySet()) {
            int time = timeHashMap.get(carNumber);
            int price = getPrice(time, fees);
            priceHashMap.put(carNumber, price);
        }

        int[] answer = new int[priceHashMap.size()];
        List<String> keyList = priceHashMap.keySet()
            .stream().sorted()
            .collect(Collectors.toList());
        for (int i = 0; i < keyList.size(); i++) {
            answer[i] = priceHashMap.get(keyList.get(i));
        }
        return answer;
    }

    static int getTime(String time) {
        String[] timeSplit = time.split(":");
        int hour = Integer.parseInt(timeSplit[0]);
        int minute = Integer.parseInt(timeSplit[1]);
        return hour * 60 + minute;
    }

    static int getPrice(int time, int[] fees) {
        int standardTime = fees[0];
        int standardPrice = fees[1];
        int unitTime = fees[2];
        int unitPrice = fees[3];

        if (time <= standardTime) {
            return standardPrice;
        } else {
            int overTime = (int) Math.ceil((float) (time - standardTime) / unitTime);
            return standardPrice + overTime * unitPrice;
        }
    }
}
