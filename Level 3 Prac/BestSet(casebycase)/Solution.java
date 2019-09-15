import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] result1 = solution(2, 9);
        int[] result2 = solution(2, 1);
        int[] result3 = solution(2, 8);
        for (int i : result1) 
            System.out.print(i + " ");
        System.out.println();
        for (int i : result2) 
            System.out.print(i + " ");
        System.out.println();
        for (int i : result3) 
            System.out.print(i + " ");
        
    }

    public static int[] solution(int n, int s) {
        if (s <= 1)
            return new int[] { -1 };

        // 와 이거 빼먹으면 실패;;;;
        if (n > s)
            return new int[] { -1 };

        
        int[] answer = new int[n];
        int share = s / n;
        int rest = s % n;

        for (int i = 0; i < n; ++i) {
            answer[i] = share;
        }
        for (int i = n - 1; i > n - rest - 1; --i) {
            answer[i] += 1;
        }
        return answer;
    }
  }