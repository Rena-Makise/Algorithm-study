import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] answer = solution(new int[] { 1, 2, 3, 2, 3 });
        for (int i : answer)
            System.out.print(i + " ");
    }

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] > prices[j]) {
                    answer[i] = j - i;
                    break;
                }
                if (j == prices.length - 1) answer[i] = j - i;
            }
        }

        return answer;
    }
}