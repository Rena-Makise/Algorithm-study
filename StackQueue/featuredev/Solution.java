import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] answer = solution(new int[] { 93,30,55 }, new int[] { 1,30,5 });
        for (int i : answer)
            System.out.print(i + " ");
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> submitList = new LinkedList<>();
        List<Integer> submitNum = new LinkedList<>();
        int maxList = 0;
        int idx = 0;


        for (int i = 0; i < progresses.length; i++) {
            submitList.offer((int)Math.ceil((double) (100 -progresses[i]) / speeds[i]));
        }

        while (!submitList.isEmpty()) {
            if (maxList >= submitList.peek()) {
                submitList.poll();
                int temp = submitNum.get(idx - 1);
                submitNum.remove(idx - 1);
                submitNum.add(temp + 1);
            } else {
                submitNum.add(1);
                maxList = submitList.poll();
                idx++;
            }
        }
        int[] answer = new int[submitNum.size()];
        for (int i = 0; i < submitNum.size(); i++) {
            answer[i] = submitNum.get(i);
        }
        return answer;
    }
}