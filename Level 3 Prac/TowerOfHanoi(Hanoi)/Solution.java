import java.util.*;

class Solution {
    static List<Integer[]> list = new ArrayList<>();
    public static void main(String[] args) {
        int[][] result = solution(2);
        for (int[] arr : result) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static int[][] solution(int n) {
        moveHanoiTower(1, 2, 3, n);
        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = Arrays.stream(list.get(i)).mapToInt(j -> j).toArray();
        }
        return answer;
    }

    /**
     * 하노이 타워 코드
     * @param first 원반들이 있던 탑
     * @param center  중간 위치 탑
     * @param last 원반들을 옮길 탑
     * @param n 원반 갯수
     */
    public static void moveHanoiTower(int first, int center, int last, int n) {
        if (n == 1) {
            list.add(new Integer[]{ first, last });
        } else {
            moveHanoiTower(first, last, center, n - 1);
            list.add(new Integer[]{ first, last });
            moveHanoiTower(center, first, last, n - 1);
        }
    }
  }