import java.util.*;

class Solution {
    static int N;
    static int[][] Board;

    public static void main(String[] args) {
        System.out.println("결과 : " + solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 }, { 0, 0, 0, 0, 0, 4, 4, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 4, 0, 0, 0 },
                { 0, 0, 0, 2, 3, 0, 0, 0, 5, 5 }, { 1, 2, 2, 2, 3, 3, 0, 0, 0, 5 },
                { 1, 1, 1, 0, 0, 0, 0, 0, 0, 5 } }));
    }


    /**
     * 블록을 채울 수 있는지 위 검사
     * @param row 세로좌표 - y
     * @param col 가로좌표 - x
     * @return
     */
    static boolean canFill(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (Board[i][col] != 0) 
                return false;
        }
        return true;
    }

    /**
     *  블록 찾기
     * @param row 세로길이
     * @param col 가로길이
     * @param h 찾을 크기 높이
     * @param w 찾을 크기 너비
     * @return
     */
    static boolean find(int row, int col, int h, int w) {
        int emptyCnt = 0;
        int lastValue = -1;
        for (int r = row; r < row + h; ++r) {
            for (int c = col; c < col + w; ++c) {
                if (Board[r][c] == 0) {
                    if (!canFill(r, c))  // 먄약 채울 수 없으면
                        return false;
                    if (++emptyCnt > 2)  // 만약 빈 공간이 2개가 넘어가면
                        return false;
                }
                else {
                    if (lastValue != -1 && lastValue != Board[r][c])  // 만약 최근 블록과 다른 블록이 나타나면
                        return false;
                    lastValue = Board[r][c];
                }
            }
        }

        for (int r = row; r < row + h; ++r)
            for (int c = col; c < col + w; ++c)
                Board[r][c] = 0;  // 0으로 블록을 없애준다.

        return true;
    }

    public static int solution(int[][] board) {
        Board = board;
        N = board.length;
        int answer = 0;
        int cnt;

        do {
            cnt = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (i <= N - 2 && j <= N - 3 && find(i, j, 2, 3)) {
                        ++cnt;
                    } else if (i <= N - 3 && j <= N - 2 && find(i, j, 3, 2)) {
                        ++cnt;
                    }
                }
            }
            answer += cnt;
        } while (cnt != 0); // 더 이상 만족하는 블록이 없을 때 까지.
        
        return answer;
    }
}