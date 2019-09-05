import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] result1 = solution(new String[] { "I 16", "D 1" });
        int[] result2 = solution(new String[] { "I 7","I 5","I -5","D -1" });

        for (int i : result1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : result2) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(String[] operations) {
        Queue<Integer> que = new PriorityQueue<>();
        Queue<Integer> reverseQue = new PriorityQueue<>(Collections.reverseOrder());
        int[] answer;

        for (int i = 0; i < operations.length; ++i) {
            String[] temp = operations[i].split(" ");
            if (temp[0].equals("I")) {
                que.offer(Integer.parseInt(temp[1]));
                reverseQue.offer(Integer.parseInt(temp[1]));
            } else if (temp[0].equals("D")) {
                // 한쪽 큐 지울때 다른쪽 큐도 지우는 과정을 거쳐야 하는듯...
                if (temp[1].equals("1") && !reverseQue.isEmpty()) {
                    List<Integer> tempList = new ArrayList<>();
                    int delNum = reverseQue.poll();
                    int tempNum;
                    while (!que.isEmpty()) {
                        tempNum = que.poll();
                        if (tempNum == delNum) break;
                        tempList.add(tempNum);
                    }
                    que.addAll(tempList);
                } else if (temp[1].equals("-1") && !que.isEmpty()) {
                    List<Integer> tempList = new ArrayList<>();
                    int delNum = que.poll();
                    int tempNum;
                    while (!reverseQue.isEmpty()) {
                        tempNum = reverseQue.poll();
                        if (tempNum == delNum) break;
                        tempList.add(tempNum);
                    }
                    reverseQue.addAll(tempList);
                }
            }
        }
        if (que.isEmpty() || reverseQue.isEmpty()) {
            answer = new int[] { 0, 0 };
        } else {
            answer = new int[] { reverseQue.peek(), que.peek() };
        }

        return answer;
    }
}