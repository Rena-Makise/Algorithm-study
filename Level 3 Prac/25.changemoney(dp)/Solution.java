import java.util.*;


// 아이디어 : https://ydeer.tistory.com/59
// 1원일때는 그냥 한가지 경우밖에 없다.
// 그 이후부터는 자기 자신일때부터 변화가 시작. 2원일때는 2원부터, 3원일땐 3원부터 한 가지 경우가 더 늘어난다.
// 변화가 일어나기 시작한 이후부터는 자기 위의 수 + n칸 왼쪽 수(n : 추가된 동전 단위)
class Solution {
    static final int DIV = 1000000007;
    public static void main(String[] args) {
        System.out.println(solution(5, new int[] { 1, 2, 5 }));   
    }

    /**
     *  거스름돈 (dp)
     * @param n  거슬러 줘야 하는 금액
     * @param money  보유하고 있는 돈의 종류
     * @return
     */
    public static int solution(int n, int[] money) {
        int answer = 0;
        Arrays.sort(money);
        // // 이차원 배열로 시도 시 효율성 실패!
        // int[][] dp = new int[money.length][n];
        // // 첫 행은 최소동전의 배수(%=0)만 1을 채워주고 나머지는 0
        // for (int i = 0; i < n; ++i)
        //     if ((i + 1) % money[0] == 0)
        //         dp[0][i] = 1;

        // // 그 다음 행 부터는 0 ~ 자기자신 전까지는 그대로 위의 수 복사
        // // 자기자신번째는 위의 수 + 1
        // // 자기자신번째 이후는 위의 수 + n칸 이전수(n은 추가된 동전 단위)
        // for (int i = 1; i < money.length; ++i) {
        //     int current = money[i];
        //     for (int j = 0; j < n; ++j) {
        //         if (j == current - 1)
        //             dp[i][j] = (dp[i-1][j] + 1) % DIV;
        //         else if (j < current - 1) 
        //             dp[i][j] = dp[i - 1][j];
        //         else
        //             dp[i][j] = (dp[i - 1][j] + dp[i][j - current]) % DIV;
        //     }
        // }
        
        // 2차원 배열을 일차원 배열로 압축시킨다.
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i)
            if ((i + 1) % money[0] == 0) 
                dp[i] = 1;

        for (int i = 1; i < money.length; ++i) {
            int current = money[i];
            // j는 current - 1부터. 당연히 그 이전은 똑같으니까.
            for (int j = current - 1; j < n; j++) {
                if (j == current - 1)
                    dp[j] = (dp[j] + 1) % DIV;
                else if (j > current - 1)
                    dp[j] = (dp[j] + dp[j - current]) % DIV;
            }
        }

        answer = dp[n-1];
        return answer;
    }
  }