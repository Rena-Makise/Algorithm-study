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

        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {  // 삼각형 왼쪽 사이드의 경우
                    allSum[i][j] = allSum[i - 1][0] + triangle[i][0];
                } else if (i == j) {  // 삼각형 오른쪽 사이드의 경우
                    allSum[i][j] = allSum[i - 1][j - 1] + triangle[i][j];
                } else {  // 삼각형 중앙 부분의 경우
                    allSum[i][j] = Math.max(allSum[i - 1][j - 1], allSum[i - 1][j]) + triangle[i][j];
                }
                if (answer < allSum[i][j]) answer = allSum[i][j];
            }
        }
        
        return answer;
    }
}