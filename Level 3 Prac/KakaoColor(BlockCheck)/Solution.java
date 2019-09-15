import java.util.*;

class Solution {
    static int[][] area;
    static boolean[][] map;  // 와 이거 안쓰니까 정확성 에러나는;;;;;;;;;;;;;;;;;
    
    public static void main(String[] args) {
        int[] result = solution(6, 4, new int[][] { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 }, { 0, 0, 0, 3 } });
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    /**
     * 해당 타입 영역 체킹 함수
     * @param type  영역 타입
     * @param x   x좌표
     * @param y  y좌표
     * @param m   x최대값(영역)
     * @param n  y최대값(영역)
     * @return  해당 영역 타입 크기
     */
    public static int checkArea(int type, int x, int y, int m, int n) {
        if (x >= m || y >= n || x < 0 || y < 0 || map[x][y] == true || type != area[x][y]) {
            return 0;
        }

        map[x][y] = true; // 지나간 영역 체킹
        int cnt = 1;  // 자기 자신 체킹 +1

        cnt += checkArea(type, x - 1, y, m, n); // 왼쪽 체크
        cnt += checkArea(type, x, y - 1, m, n); // 위쪽 체크
        cnt += checkArea(type, x + 1, y, m, n); // 오른쪽 체크
        cnt += checkArea(type, x, y + 1, m, n); // 아래쪽 체크

        return cnt;
    }


    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        area = picture;
        map = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (area[i][j] == 0)
                    continue;

                int checkResult = checkArea(area[i][j], i, j, m, n);

                maxSizeOfOneArea = (checkResult > maxSizeOfOneArea) ? checkResult : maxSizeOfOneArea;
                numberOfArea = checkResult > 0 ? numberOfArea + 1 : numberOfArea;

            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
  }