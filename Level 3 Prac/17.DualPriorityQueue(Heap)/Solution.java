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
        Queue<Integer> que = new PriorityQueue<>(); // 순방향 우선순위 큐
        Queue<Integer> reverseQue = new PriorityQueue<>(Collections.reverseOrder()); // 역방향 우선순위 큐
        int[] answer;

        for (int i = 0; i < operations.length; ++i) {
            String[] temp = operations[i].split(" ");
            if (temp[0].equals("I")) {
                que.offer(Integer.parseInt(temp[1]));
                reverseQue.offer(Integer.parseInt(temp[1]));
            } else if (temp[0].equals("D")) {
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