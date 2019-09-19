import java.util.*;

class Solution {
    static boolean[][] link;

    public static void main(String[] args) {
        System.out.println(solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }));
        System.out.println(solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } }));
    }

    /**
     * 깊이/너비 우선 탐색
     * @param computers  네트워크 배열
     * @param idx  현재 탐색하고자 하는 컴퓨터 idx
     * @param n  전체 컴퓨터 갯수
     */
    public static void dfs(int[][] computers, int idx, int n) {
        for (int i = 0; i < n; i++) {
            if (computers[idx][i] == 1 && !link[idx][i]) {
                link[idx][i] = link[i][idx] = true;
                dfs(computers, i, n);
            }
        }
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        link = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (!link[i][i]) {
                dfs(computers, i, n);
                answer++;
            }
            
        }

        return answer;
    }
}