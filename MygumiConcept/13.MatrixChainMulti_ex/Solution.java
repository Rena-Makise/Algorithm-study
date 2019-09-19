import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(soluion(4, new int[] { 40, 30, 30, 50 }));
        System.out.println(soluion(15, new int[] { 1, 21, 3, 4, 5, 35, 5, 4, 3, 5, 98, 21, 14, 17, 32 }));
    }

    /**
     * 파일 합치기
     * 연쇄 행렬 곱셈 응용
     * 두 개의 파일을 합칠 때 필요한 비용이 두 파일 크기의 합이라고 가정
     *  최종적인 한 개의 파일을 완성하는데 필요한 비용의 총 합을 계산
     * @param n  소설을 구성하는 장의 수
     * @param arr  1장부터 K장까지 수록한 파일의 크기를 나타낸 배열
     * @return
     */
    public static int soluion(int n, int[] arr) {
        int[] sum = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
 
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }
 
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
 
                for(int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n];
    }
}