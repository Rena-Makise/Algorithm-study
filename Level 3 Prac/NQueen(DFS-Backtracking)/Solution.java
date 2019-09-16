import java.util.*;

// DFS(깊이 우선 탐색)를 이용한 Backtracking문제
// https://mygumi.tistory.com/199 참고
// https://mygumi.tistory.com/102?category=677288 참고
class Solution {
    static int answer = 0;
    static int[] col;

    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static void dfs(int row, int n) {
        if(row == n) {
            answer++;
        } else {
            for (int i = 1; i <= n; ++i) {
                col[row + 1] = i;
                if (isPossible(row + 1)) {
                    dfs(row + 1, n);
                } else {
                    col[row + 1] = 0;
                }
            }
        }
        col[row] = 0;
    }

    public static boolean isPossible(int c) {
        // 이전 열들을 탐색하면서 배치 가능 여부 확인
        for (int i = 1; i < c; ++i) {
            // 같은 행이나 열인가?
            if (col[i] == col[c])
                return false;
            // 대각선에 위치해 있는가?
            if (Math.abs(col[i] - col[c]) == Math.abs(i - c))
                return false;
        }
        return true;
    }

    public static int solution(int n) {
        for (int i = 1; i <= n; ++i) {
            col = new int[n + 1];
            // 정점은 행을 기준
            // col[1]의 의미는 1행 *열이다.
            // col[1] = 1 => 1행 1열, col[2] = 3 => 2행 3열
            col[1] = i;
            dfs(1, n);
        }
        return answer;
    }
}