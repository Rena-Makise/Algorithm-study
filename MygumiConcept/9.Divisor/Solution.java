import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] result1= getDivisorOld(10);
        int[] result2 = getDivisorNew(10);

        for(int i : result1) {
            System.out.print(i + " ");
        }

        System.out.println();

        for(int j : result2) {
            System.out.print(j + " ");
        }
    }

    /**
     * 1부터 n까지 각각의 수에 대해 약수의 개수를 반환
     * 가장 일반적인 약수 구하는 알고리즘
     * 1부터 k까지 k의 나머지를 구해 0이라면 약수라고 판단
     * 하지만 너무 오래 걸린다!
     * @param n
     * @return
     */
    public static int[] getDivisorOld(int n) {
        int cnt = 0;
        int[] count = new int[n + 1];

        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i * i <= k; ++i) {
                // k의 가장 큰 약수는 k를 제외하면 최대 k의 절반이므로!
                if (k % i == 0) {
                    cnt++;
                    if (i * i < k) {
                        cnt ++;
                    }
                }
            }
            count[k] = cnt;
            cnt = 0;
        }
        return count;
    }

    /**
     * 1부터 n까지 각각의 수에 대해 약수의 개수를 반환
     * 각 수에 대해 배수를 확인한다면 약수의 개수를 더해나가면서 총 개수를 구할 수 있다!
     * @param n
     * @return
     */
    public static int[] getDivisorNew(int n) {
        int[] count = new int[n + 1]; 

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n / i; ++j) {
                count[i * j]++;
            }
        }

        return count;
    }
}