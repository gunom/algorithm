public class Pr43238 {
    public long solution(int n, int[] times) {
        long answer = 0;
        long mid = 0;
        long left = 0;
        long right = (long) times[times.length-1] * n;
        while (left <= right){
            mid = (left + right) / 2;
            long cnt = 0;
            for (int time : times) {
                cnt += mid / time;
            }
            if(cnt >= n){
                if(answer == 0){
                    answer = mid;
                } else {
                    answer = Math.min(answer, mid);
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
}
