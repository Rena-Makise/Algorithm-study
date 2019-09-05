import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } }));
    }
    
    public static int solution(int[][] triangle) {
        int answer = 0;
        int length = triangle.length;
        int[][] allSum = new int[length][length];
        allSum[0][0] = triangle[0][0];

        // 삼각형의 가장 왼쪽 값과 오른쪽 값을 구하는 방법은 다르다!
        // 가운데는 좌 우 중 최댓값을 선택해 더하면 된다.
        // 따라서 세가지 케이스로 구분
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    allSum[i][j] = allSum[i - 1][0] + triangle[i][0];
                } else if (i == j) {
                    allSum[i][j] = allSum[i - 1][j - 1] + triangle[i][j];
                } else {
                    allSum[i][j] = Math.max(allSum[i - 1][j - 1], allSum[i - 1][j]) + triangle[i][j];
                }
                if (answer < allSum[i][j]) answer = allSum[i][j];
            }
        }
        
        return answer;
    }
}