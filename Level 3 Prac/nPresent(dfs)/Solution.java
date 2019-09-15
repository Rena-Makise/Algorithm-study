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

    /**
     * 연산 동적계획법
     * @param N  특정 숫자 
     * @param number  연산으로 만들어야 하는 수
     * @param cnt  숫자 자릿수 - 1
     * @param prev  이전까지 연산 결과
     */
    public static void dfs(int N, int number, int cnt, int prev) {
        int tempN = N;
        if (cnt > 8) {
            answer = -1;
            return;
        }

        if (prev == number) {
            if (answer == -1 || answer > cnt)
                answer = cnt;
            return;
        }

        for (int i = 0; i < 8 - cnt; ++i) {
            dfs(N, number, cnt + i + 1 , prev + tempN);
            dfs(N, number, cnt + i + 1 , prev - tempN);
            dfs(N, number, cnt + i + 1 , prev * tempN);
            dfs(N, number, cnt + i + 1 , prev / tempN);

            tempN = increaseNumber(tempN, N);
        }
    }

    public static int increaseNumber(int value, int N) {
        return value * 10 + N;
    }
}