import java.util.*;

class Solution {
    static int ANSWER = 0;
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 1, 1, 1, 1, 1 }, 3));
    }

    /**
     * 깊이 우선 탐색
     * @param target
     * @param numbers
     * @param k
     */
    public static void dfs(int target, int[] numbers, int k) {
        if(k == numbers.length) {
            int sum = 0;
            for(int i = 0; i < numbers.length; ++i) {
                sum += numbers[i];
            }
            if (sum == target)
                ANSWER++;
            return;
        } else {
            numbers[k] *= 1;
            dfs(target, numbers, k + 1);

            numbers[k] *= -1;
            dfs(target, numbers, k + 1);
        }
    }

    public static int solution(int[] numbers, int target) {
        dfs(target, numbers, 0);
        return ANSWER;
    }
}