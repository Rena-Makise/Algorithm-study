import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] answer = solution(new int[] { 1, 5, 2, 6, 3, 7, 4 }, new int[][] { {2, 5, 3}, { 4, 4, 1 }, {1, 7, 3} });
        for (int i : answer) 
            System.out.print(i + " ");
    }
    public static int[] solution(int[] array, int[][] commands) {
        List<Integer> temp = new ArrayList<>();
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; ++i) {
            for (int j = commands[i][0] - 1; j < commands[i][1]; ++j) {
                temp.add(array[j]);
            } 
            Collections.sort(temp);
            answer[i] = temp.get(commands[i][2] - 1);
            temp.clear();
        }
        return answer;
    }
}