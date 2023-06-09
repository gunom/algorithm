import java.util.Arrays;

public class Pr43236 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int low = 1;
        int high = distance;

        Arrays.sort(rocks);

        while(low<=high){
            int mid = (low+high)/2;
            int prev = 0;
            int count = 0;

            for(int i=0; i<rocks.length; i++){
                if(rocks[i]-prev < mid){
                    count++;
                }else{
                    prev = rocks[i];
                }
            }

            if(count > n){
                high = mid-1;
            }
            else{
                answer = mid;
                low = mid+1;
            }
        }
        return answer;
    }
}
