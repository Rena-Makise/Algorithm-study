import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] { { 123, 1, 1 }, { 356, 1, 0 }, { 327, 2, 0 }, { 489, 0, 1 } }));    
    }

    public static int solution(int[][] baseball) {
        int answer = 0;

        // 숫자 야구는 같은 숫자가 나오면 안된다. 그리고 0도 안된다.
        // 따라서 숫자 야구에서 가장 작은 수는 123, 가장 큰 수는 987
        for (int i = 123; i <= 987; ++i) {
            int x = i % 10; // 1의 자리수
            int y = (i / 10) % 10; // 10의 자리수
            int z = i / 100; // 100의 자리수

            // 숫자 야구에서는 0이 나올 수 없다.
            if (x == 0 || y == 0 || z == 0) continue;
            // 또한 숫자 야구에서는 같은 숫자가 나올 수 없다.
            if ((x == y) || (y == z) || (x == z)) continue;

            for (int j = 0; j < baseball.length; ++j) {
                int strike = 0;
                int ball = 0;

                int questNum = baseball[j][0];
                int questNum_x = questNum % 10; // 1의 자리수
                int questNum_y = (questNum / 10) % 10; // 10의 자리수
                int questNum_z = questNum / 100; // 100의 자리수

                if (questNum_x == 0 || questNum_y == 0 || questNum_z == 0) break;
                if ((questNum_x == questNum_y) || (questNum_y == questNum_z) || (questNum_x == questNum_z)) break;

                if (x == questNum_x) strike++;
                if (y == questNum_y) strike++;
                if (z == questNum_z) strike++;
                if (strike != baseball[j][1]) break; // 스트라이크 수가 다르면 정답이 아님

                if((x == questNum_y) || (x == questNum_z)) ball++;
                if((y == questNum_x) || (y == questNum_z)) ball++;
                if((z == questNum_x) || (z == questNum_y)) ball++;
                if(ball != baseball[j][2]) break; // 볼 수가 다르면 정답이 아님

                if (j == baseball.length - 1) answer++; // 조건을 모두 만족시키면 답이 될 수 있음
            }

        }

        return answer;
    }
}