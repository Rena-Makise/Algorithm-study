import java.util.*;

class Solution {
    static boolean[][] link;

    public static void main(String[] args) {
        System.out.println(solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }));
        System.out.println(solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } }));
    }

    public static void dfs(int[][] computers, int idx, int n) {
        for (int i = 0; i < n; i++) {
            // 연결된 적이 없는 경우 지금 노드와 연결된 다른 노드를 찾기 위해 재귀함수 호출
            if (computers[idx][i] == 1 && !link[idx][i]) {
                link[idx][i] = link[i][idx] = true;
                dfs(computers, i, n);
            }
        }
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        link = new boolean[n][n]; // 기본적으로 false로 초기화

        for (int i = 0; i < n; i++) {
            // i,i가 연결되지 않았따는 것은 아직 방문한적이 없는 것.
            if (!link[i][i]) {
                dfs(computers, i, n);
                answer++;
            }
            
        }

        return answer;
    }
}