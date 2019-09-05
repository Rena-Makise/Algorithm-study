import java.util.*;

class Solution {
    static int answer = -1;
    public static void main(String[] args) {
        System.out.println(solution(5, 12)); 
        System.out.println(solution(2, 11)); 
    }

    public static int solution(int N, int number) {
        dfs(N, number, 0, 0);
        return answer;
    }

    public static void dfs(int N, int number, int cnt, int prev) {
        int temp_N = N;
        if (cnt > 8) {
            answer = -1;
            return;
        }

        if (number == prev) {
            if (answer == -1 || answer > cnt)
                answer = cnt;
            return;
        }

        // 모든 사칙연산 케이스
        for (int i = 0; i < 8 - cnt; ++i) {
            dfs(N, number, cnt + i + 1, prev + temp_N);
            dfs(N, number, cnt + i + 1, prev - temp_N);
            dfs(N, number, cnt + i + 1, prev * temp_N);
            dfs(N, number, cnt + i + 1, prev / temp_N);

            temp_N = temp_N * 10 + N;
        }
    }
}