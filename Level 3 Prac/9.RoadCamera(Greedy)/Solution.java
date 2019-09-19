import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] { { -20,15 }, { -14,-5 }, { -18,-13 }, { -5,-3 } }));
    }

    public static int solution(int[][] routes) {
        int answer = 0;
        int min = Integer.MIN_VALUE;

        // 도착시간을 기준으로 정렬한다.
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1],  b[1]));

        for (int[] arr : routes) {
            if (min < arr[0]) {
                min = arr[1];
                answer++;
            }
        }
        return answer;
    }
}