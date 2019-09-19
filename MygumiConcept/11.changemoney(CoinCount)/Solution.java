import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(soluiton(3, 15, new int[] { 1, 5, 12 }));
    }

    /**
     * 사용한 동전의 최소 갯수 출력
     * @param n  n가지 종류의 동전
     * @param k  가치의 합을 k원으로
     * @param arr  동전 종류 배열
     * @return
     */
    public static int soluiton(int n, int k, int[] arr) {
        int[] dp = new int[k + 1];

        Arrays.fill(dp, 100001);  // 그냥 나올 수 없는 큰 수 설정, INF
        dp[0] = 0;

        for (int i = 0; i < n; ++i) {
            int current = arr[i];
            for (int j = current; j <= k; ++j) {
                dp[j] = Math.min(dp[j], dp[j - current] + 1);
            }
        }

        return (dp[k] == 100001 ? -1 : dp[k]);
    }
}