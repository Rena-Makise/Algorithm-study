import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 120, 110, 140, 150 }, 485));
    }

    /**
     * 이분탐색
     * @param budgets  요청 예산 배열
     * @param M  국가 총 예산
     * @return
     */
    public static int solution(int[] budgets, int M) {
        int answer = 0;
        Arrays.sort(budgets);

        int length = budgets.length;
        int min = 0;
        int max = M;
        int mid = 0;
        int premid = 0;
        long sum = 0;

        for (int i : budgets) {
            sum += i;
        }

        if (sum < M) {
            answer += budgets[length - 1];
            return answer;
        } else {
            while (true) {
                sum = 0;
                mid = (max + min) / 2;

                if (mid == premid) {
                    break;
                }

                for (int i = 0; i < length; ++i) {
                    if (mid <= budgets[i]) {
                        sum += mid * (length - i);
                        break;
                    } else {
                        sum += budgets[i];
                    }
                }

                if (sum <= M) {
                    min = mid;
                } else {
                    max = mid;
                }
                premid = mid;
            }
            answer = mid;
        }

        return answer;
    }
}