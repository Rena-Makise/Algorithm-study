import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 3, 1, 6, 2, 7, 30, 1 }));
    }
    
    public static int solution(int[] weight) {
        int answer = 1;
        Arrays.sort(weight);
        
        // 누적된 값보다 가지고있는 추의 무게가 크게되면 도달할 방법이 없다.
        for (int i = 0 ; i < weight.length; ++i) {
            if (answer < weight[i])
                break;
            answer += weight[i];
        }

        return answer;
    }
}