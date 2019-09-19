import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    /**
     * 피보나치 수열 코드 (재귀)
     * @param n  몇 번째?
     * @return
     */
    public static int Fibo(int n) {
        if (n <= 2) 
            return 1;
        else 
            return Fibo(n - 1) + Fibo(n - 2);
    }

    /**
     * 피보나치 수열 (다이나믹)
     * @param n
     * @return
     */ 
    public static int dynamicFibo(int n) {
        int last1, last2, result = 0;

        if (n <= 2)
            return 1;
        
        last1 = 1;
        last2 = 1;

        for (int i = 2; i < n; ++i) {
            result = last1 + last2;
            last1 = last2;
            last2 = result;
        }

        return result;
    }
    
    public static int solution(int n) {
        int answer = 0;

        if (n <= 1)
            return 1;

        int last1 = 1;
        int last2 = 1;
        for (int i = 1; i < n; ++i) {
            answer = last1 + last2;
            last1 = last2;
            last2 = answer % 1000000007;
        }

        return answer % 1000000007;
    }
  }