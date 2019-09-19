import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(10, 11));
    }

    /**
     * 거듭제곱 알고리즘
     * n번 그냥 곱해버리면 시간복잡도가 O(n)
     * 분할 정복을 통해 거듭제곱을 구하면 O(logn)으로 줄일 수 있다.
     * @param a  a를 
     * @param b  b번 곱한 수를 구하라
     * @return
     */
    public static long solution(int a, int b) {
        if (b == 0) {
            return 1;
        }

        long n = solution(a, b / 2);
        long temp = n * n;

        if (b % 2 == 0) {
            return temp;
        } else {
            return a * temp;
        }
    }
}