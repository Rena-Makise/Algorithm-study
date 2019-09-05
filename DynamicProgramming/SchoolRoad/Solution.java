import java.util.*;

class Solution {
    final static int DEVIDE = 1000000007;
    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][] { { 2, 2 } }));
    }
    public static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];

        // 물 웅덩이는 -1로 표시
        for (int i = 0; i < puddles.length; ++i) dp[puddles[i][0]][puddles[i][1]] = -1;

        dp[1][1] = 1; // 첫 스타트는 집에서
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                // 현재 서 있는 자리에 물웅덩이가 있다면 패스
                if (dp[i][j] == -1) continue;
                // 이전 자리가 길을 벗어나면 패스
                // 이전에 있던 자리에 물웅덩이가 있으면 패스
                if (i - 1 > 0 && dp[i - 1][j] != -1) dp[i][j] += dp[i - 1][j] % DEVIDE;
                if (j - 1 > 0 && dp[i][j - 1] != -1) dp[i][j] += dp[i][j - 1] % DEVIDE;
            }
        }

        // 마지막에 또 나누지 않으면 효율성 실패
        return dp[m][n] % DEVIDE;
    }
}