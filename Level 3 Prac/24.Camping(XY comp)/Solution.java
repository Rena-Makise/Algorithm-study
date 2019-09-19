import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[][] { { 0, 0 }, { 1, 1 }, { 0, 2 }, { 2, 0 } }));
    }

    public static int solution(int n, int[][] data) {
        // 좌표 압축 알고리즘

        // 2개의 ArrayList에 x, y좌표를 담는다
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            xList.add(data[i][0]);
            yList.add(data[i][1]);
        }

        // x, y좌표의 중복을 제거한다.
        ArrayList<Integer> uniqueXList = new ArrayList<>(new HashSet<>(xList));
        ArrayList<Integer> uniqueYList = new ArrayList<>(new HashSet<>(yList));

        // 오름차순 정렬
        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);

        // wedgeCnt[i][j] : (0, 0) ~ (i, j) 범위의 직사각형 내부에 존재하는 쐐기의 갯수
        int[][] wedgeCnt = new int[n][n];

        for (int i = 0; i < n; ++i) {
            int x = uniqueXList.indexOf(new Integer(data[i][0]));
            int y = uniqueYList.indexOf(new Integer(data[i][1]));

            // 좌표 압축 적용
            data[i][0] = x;
            data[i][1] = y;

            // 쐐기 갯수 초기값
            wedgeCnt[x][y] = 1;
        }

        // 쐐기 갯수 설정
        // https://jaejin0me.github.io/post/algo46/  참고
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                wedgeCnt[i][j] += (i - 1 >= 0 ? wedgeCnt[i - 1][j] : 0) 
                                        + (j - 1 >= 0 ? wedgeCnt[i][j - 1] : 0) 
                                        - (i - 1 >= 0 && j - 1 >= 0 ? wedgeCnt[i - 1][j - 1] : 0);
            }
        }
        
        int answer = 0;
        // n^2 모든 쐐기 조합에 대해 검사
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                // 조건 1 : 직사각형이 아닌 경우
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                // 조건 2 : 내부에 쐐기가 존재하는 경우
                int startX, startY, endX, endY;

                startX = Math.min(data[i][0], data[j][0]);
                startY = Math.min(data[i][1], data[j][1]);
                endX = Math.max(data[i][0], data[j][0]);
                endY = Math.max(data[i][1], data[j][1]);

                int cnt;

                // (startX, startY) < 검사범위 <= (endX-1, endY-1)
                if (startX + 1 > endX - 1 || startY + 1 > endY - 1)  {  // 직사각형 내부에 쐐기가 존재할 수 있는 공간이 있는지 체크
                    cnt = 0;
                } else { // 직사각형 내부에 쐐기가 존재하는지 체크
                    cnt = wedgeCnt[endX - 1][endY - 1] - wedgeCnt[endX - 1][startY] - wedgeCnt[startX][endY - 1] + wedgeCnt[startX][startY];
                }

                if (cnt == 0) answer++;
            }
        }

        return answer;
    }
  }