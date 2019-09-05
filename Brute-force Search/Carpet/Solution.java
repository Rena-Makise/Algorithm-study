import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] result1 = solution(10, 2);
        int[] result2 = solution(8 , 1);
        int[] result3 = solution(24, 24);

        for (int i : result1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : result2) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : result3) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] solution(int brown, int red) {
        int tileNum = brown + red;
        int[] answer = new int[2];
        
        for (int i = 3; i < tileNum; ++i) {
            if (tileNum % i == 0) {
                int row = tileNum / i;
                if ((i - 2) * (row - 2) == red) {
                    answer[0] = row;
                    answer[1] = i;
                    return answer;
                }
            }
        }

        return answer;
    }
}