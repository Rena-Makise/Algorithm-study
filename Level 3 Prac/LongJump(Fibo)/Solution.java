import java.util.*;

class Solution {
    static final long DIV = 1234567;
    public static void main(String[] args) {
        System.out.println(solution(4));
        System.out.println(solution(3));
    }
    
    // 피보나치 시발.......
    public static long solution(int n) {
        long answer = 0;

        long prev1 = 0;
        long prev2 = 1;

        for (int i = 0; i < n; ++i) {
            answer = (prev1 + prev2) % DIV;
            prev1 = prev2 % DIV;
            prev2 = answer;
        }

        return answer % DIV;
    }
  }