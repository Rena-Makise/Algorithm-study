import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 10, 20, 10, 30, 20, 50 }));
    }

    public static int solution(int[] arr) {
        int result = lis(arr);

        return result;
    }

    /**
     * 가장 긴 증가하는 부분 수열 구하기
     * Logest Increasing Subsequence 알고리즘 사용
     * O(n^2)
     * @param arr  수열
     * @return  LIS 길이 출력
     */
    public static int lis(int[] arr) {
        int[] dp = new int[arr.length];  // 인덱스마다 각 증가 수열의 길이

        int max = 0;
        dp[0] = 1;

        for (int i = 0; i < arr.length; ++i) {
            dp[i] = 1;
            // i를 기준으로 인덱스 0에서부터 i - 1까지 체크한다
            for (int j = 0; j < i; ++j) {
                if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }

            if (max < dp[i])
                max = dp[i];
        }

        return max;
    }
}
