import java.util.*;

// 가중치 없는 방향 그래프 G가 주어졌을 때, 
// 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
class Solution {
    public static void main(String[] args) {
        int[][] result = solution(3, new int[][] { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 } });
        for (int i = 1; i <= 3; ++i) {
            for (int j = 1; j <= 3; ++j) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] solution(int n, int[][] arr) {
        int[][] a = new int[n + 1][n + 1];
        int[] c = new int[n + 1];
        int[][] path = new int[n + 1][n + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                a[i][j] = arr[i - 1][j - 1];
            }
        }

        for (int i = 1; i <= n; ++i) {
            dfs(a, c, i);

            for (int j = 1; j <= n; ++j) {
                path[i][j] = c[j];
            }
            Arrays.fill(c, 0);
        }

        return path;
    }

    // 재귀 DFS - 인접행렬
    public static void dfs(int[][] a, int[] c, int v) {
        int n = a.length - 1;

        for (int i = 1; i <= n; ++i) {
            if (a[v][i] == 1 && c[i] == 0) {
                c[i] = 1;
                dfs(a, c, i);
            }
        }
    }
}