import java.util.*;

class Pair {
    int idx;
    int value;

    public Pair (int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}

class Solution {
    static int[] dp;
    public static void main(String[] args) {
        int[] result = solution(6, new int[] { 10, 20, 10, 30, 20, 50 });

        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static int lower_bound(int end, int n) {
        int start = 0;

        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] >= n) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    /**
     * 가장 긴 증가하는 부분 수열
     * Longest Increasing Subsequcne 사용
     * 이분 탐색 이용 - O(nlogn)
     * @param n  수열의 크기
     * @param arr  수열
     * @return  LIS 출력
     */
    public static int[] solution(int n, int[] arr) {
        Pair[] tracking = new Pair[n];
        dp = new int[n];
        dp[0] = arr[0];
        tracking[0] = new Pair(0, arr[0]);
        int idx = 0;
        for (int i = 1; i < n; ++i) {
            if (dp[idx] < arr[i]) {
                dp[++idx] = arr[i];

                tracking[i] = new Pair(idx, arr[i]);
            } else {
                int ii = lower_bound(idx, arr[i]);
                dp[ii] = arr[i];

                tracking[i] = new Pair(ii, arr[i]);
            }
        }

        Stack<Integer> stack = new Stack<>();
        int temp = idx;
        for (int i = n - 1; i >= 0; --i) {
            if (temp == tracking[i].idx) {
                stack.push(tracking[i].value);
                --temp;
            }
        }

        int[] answer = new int[stack.size()];
        for (int i = 0; i < answer.length; ++i) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}