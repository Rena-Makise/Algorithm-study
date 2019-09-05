import java.util.*;

class Solution {
    static ArrayList<ArrayList<String>> Board;

    public static void main(String[] args) {
        System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        System.out.println(solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }

    /**
     * 삭제 가능한 블록 탐색
     * @param board
     * @return
     */
    static int find(ArrayList<ArrayList<String>> board) {
        int cnt = 0;
        char[][] delMarkBoard = new char[board.size()][board.get(0).size()];

        for (int i = 0; i < board.size() ; ++i) {
            for (int j = 0; j < board.get(i).size(); ++j) {
                String cur = board.get(i).get(j);
                if (!cur.isEmpty()) {
                    if ((cur.equals(getValue(i - 1, j, board)) && cur.equals(getValue(i, j - 1, board)) && cur.equals(getValue(i - 1, j - 1, board)))
                    || (cur.equals(getValue(i + 1, j, board)) && cur.equals(getValue(i, j - 1, board)) && cur.equals(getValue(i + 1, j - 1, board))) 
                    || (cur.equals(getValue(i - 1, j, board)) && cur.equals(getValue(i, j + 1, board)) && cur.equals(getValue(i - 1, j + 1, board)))
                    || (cur.equals(getValue(i + 1, j, board)) && cur.equals(getValue(i, j + 1, board)) && cur.equals(getValue(i + 1, j + 1, board)))) {
                        delMarkBoard[i][j] = 'D';
                        cnt++;
                    }
                }
            }
        }

        for (int i = 0; i < delMarkBoard.length; i++) {
            for (int j = 0; j < delMarkBoard[i].length; j++) {
                if (delMarkBoard[i][j] == 'D')
                    board.get(i).set(j, "");
            }
        }

        return cnt;
    }

    /**
     * 공백 채우기 (역방향 탐색)
     * @param board
     */
    static void reArrange(ArrayList<ArrayList<String>> board) {
        for (int i = board.size() - 1; i >= 0; --i) {
            for (int j = board.get(i).size() - 1; j >= 0; --j) {
                if (board.get(i).get(j).isEmpty()) {
                    for (int k = i; k >= 0; --k) {
                        // 공백이 아닌 문자를 발견하면 스왑
                        if(!board.get(k).get(j).isEmpty()) {
                            board.get(i).set(j, board.get(k).get(j));
                            board.get(k).set(j, "");
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 탐색 배열이 인덱스가 벗어난 경우 처리를 위한 함수
     * @param i
     * @param j
     * @param board
     * @return
     */
    static String getValue(int i, int j, ArrayList<ArrayList<String>> board) {
        String retValue = "";
        try {
            retValue = board.get(i).get(j);
        } catch(IndexOutOfBoundsException e) {
            retValue = "";
        }
        return retValue;
    }

    public static int solution(int m, int n, String[] board) {
        // 입력받은 스트링 배열을 2차원 리스트로 전환
        Board = new ArrayList<ArrayList<String>>();
        for (String str : board) {
            ArrayList<String> innerList = new ArrayList<>();
            for (char c : str.toCharArray())
                innerList.add(String.valueOf(c));
            Board.add(innerList);
        }

        int answer = 0;
        int cnt = 0;

        while((cnt = find(Board)) > 0) {
            answer += cnt;
            reArrange(Board);
        }

        return answer;
    }
  }