import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(6, new int[] { 7, 10 }));
    }

    public static long passCount(long mid, int[] times) {
        long count = 0;
        for (int time : times) {
            if (time > mid) continue;
            count += (mid / time);
        }
        return count;
    }

    // 이분 탐색
    public static long solution(int n, int[] times) {
        long max = 0;

        for (int i : times) {
            if (max < i) max = i;
        }

        long low = 1;
        long high = max * n; // 최악의 상황
        long answer = 0;

        while (low + 1 < high) {
            long mid = (low + high) / 2;
            long count = passCount(mid, times);
            if (count < n) low = mid;
            else high = mid;
        }

        if (passCount(low, times) >= n) 
            answer = low;
        else
            answer = high;

        return answer;
    }
}