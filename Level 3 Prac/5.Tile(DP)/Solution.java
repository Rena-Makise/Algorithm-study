import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(6));
    }

    public static long solution(int N) {
        long prev = 1;
        long cur = 1;
        long answer = 0;

        for (int i = 1; i < N - 1; ++i) {
            long temp = prev;
            prev = cur;
            cur = cur + temp;
        }

        answer = cur * 2 + (cur + prev) * 2;

        return answer;
    }
}