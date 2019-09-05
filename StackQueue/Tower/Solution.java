import java.util.*;

class Tower {
    int idx;
    int height;
    public Tower(int idx, int height) {
        this.idx = idx;
        this.height = height;
    }
}

class Solution {
    public static void main(String[] args) {
        int[] answer1 = solution(new int[] { 6, 9, 5, 7, 4 });
        int[] answer2 = solution(new int[] { 3, 9, 9, 3, 5, 7, 2 });
        int[] answer3 = solution(new int[] { 1, 5, 3, 6, 7, 6, 5 });
        for (int n : answer1) {
            System.out.print(n + " ");
        }
        System.out.println();
        for (int n : answer2) {
            System.out.print(n + " ");
        }
        System.out.println();
        for (int n : answer3) {
            System.out.print(n + " ");
        }
    }

    public static int[] solution(int[] heights) {
        List<Tower> list = new LinkedList<>();
        int[] answer = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            list.add(new Tower(i, heights[i]));
        }

        for (int i = heights.length - 1; i > 0; --i) {
            for (int j = i - 1; j >= 0; --j) {
                if (list.get(i).height < list.get(j).height) {
                    answer[i] = j + 1;
                    break;
                } else {
                    answer[i] = 0;
                }
            }
        }
        answer[0] = 0;
        return answer;
    }
}