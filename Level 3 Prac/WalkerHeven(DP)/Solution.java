import java.util.*;

class Solution {
    final static int MOD = 20170805;

    public static void main(String[] args) {
        System.out.println(solution(3, 3, new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }));
        System.out.println(solution(3, 6, new int[][] { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } }));
    }

    public static int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        // 오른쪽으로 가는 경우와 아랫쪽으로 가는 경우를 나누어서 생각하자!!!
        int[][] right = new int[m + 1][n + 1];
        int[][] bottom = new int [m + 1][n + 1];

        right[1][1] = 1;
        bottom[1][1] = 1;

        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                if (cityMap[i - 1][j - 1] == 0) {
                    // 0인 경우 각자 왼쪽이나 위쪽에서 오는 경우를 더한다.
                    right[i][j] = (right[i][j] + right[i][j - 1] + bottom[i - 1][j]) % MOD;
                    bottom[i][j] = (bottom[i][j] + right[i][j - 1] + bottom[i - 1][j]) % MOD;
                } else if (cityMap[i - 1][j - 1] == 1) {
                    // 1인 경우 막혀서 갈 수 없다.
                    right[i][j] = 0;
                    bottom[i][j] = 0;
                } else {
                    // 2인 경우 왼쪽에서 오는 차량은 오른쪽으로만,
                    // 위쪽에서 오는 차량은 아랫쪽으로만 갈 수 있다.
                    right[i][j] = right[i][j - 1];
                    bottom[i][j] = bottom[i - 1][j];
                }
            }
        }

        answer = (right[m][n - 1] + bottom[m - 1][n]) % MOD;

        return answer;
    }
  }