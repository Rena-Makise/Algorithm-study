import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(3, new int[][] { { 5, 3 }, { 3, 2 }, { 2, 6 } }));
    }
    
    /**
     * 행렬 곱셈 연산 횟수의 최솟값 출력
     * 연쇄 행렬 곱셈 알고리즘 이용
     * @param n  행렬의 갯수
     * @param arr  행렬의 크기 배열
     * @return
     */
    public static int solution(int n, int[][] arr) {
        int[][] m = new int[n + 1][n + 1];
        int[] d = new int[2 * n + 1];

        for (int i = 0; i < n; ++i) {
            d[i] = arr[i][0];
            d[i + 1] = arr[i][1];
        }

        for (int len = 2; len <= n; ++len) {
            for (int i = 1; i <= n - len + 1; ++i) {
                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    int cost = m[i][k] + m[k + 1][j] + d[i - 1] * d[k] * d[j];
                    m[i][j] = Math.min(m[i][j], cost);
                }
            }
        }
        return m[1][n];
    }
}