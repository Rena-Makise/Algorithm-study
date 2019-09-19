import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println("5는 소수? " + isSosu(5));
        System.out.print("101이하의 소수 : ");
        boolean[] result = sosuList(101);
        for (int i = 2; i < result.length;  ++i) {
            if (result[i] == false)
                System.out.print(i + " ");
        } 
    }

    /**
     * 소수인지 아닌지 판별
     * @param n  판별하고자 하는 수
     * @return
     */
    public static boolean isSosu(int n) {
        boolean sosu = true;
        if (n <= 1)
            return sosu;
        for (int j = 2; j <= Math.sqrt(n); ++j) {
            if (n % j == 0) {
                sosu = false;
                break;
            }
        }
        return sosu;
    }

    /**
     * 2부터 n까지 소수 출력 - 에라토스테네스의 체
     * @param n
     * @return
     */

    public static boolean[] sosuList(int n) {
        // false이면 소수인 것으로!
        boolean[] arr = new boolean[n + 1];
        arr[0] = arr[1] = true;

        // 2부터 숫자를 키워가며 배수들을 제외 (true 할당)
        for (int i = 2; i * i <= n; ++i) {
            for (int j = i * i; j <= n; j += i) {
                arr[j] = true;
            }
        }
        return arr;
    }
}