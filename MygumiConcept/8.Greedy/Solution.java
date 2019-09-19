import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(10, 4200, new int[] { 1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000 }));
    }

    /**
     * 그리디 동전문제
     * 전체를 고려하지 않고 그 순간에서의 최선을 선택하는 알고리즘
     * 항상 그 선택이 최적이라고 확신할 수 없다.
     * @param n  동전의 종류 갯수
     * @param k  동전으로 만들고 싶은 가치의 합
     * @param arr  동전의 종류
     * @return
     */
    public static int solution(int n, int k, int[] arr) {
        int a = 0;
        int cnt = 0;
        for (int i = arr.length - 1; i >= 0; --i) {
            a = arr[i];
            if (k / a > 0) {
                cnt += k / a;
                k -= a * (k / a);
            }
        }

        return cnt;
    }
}