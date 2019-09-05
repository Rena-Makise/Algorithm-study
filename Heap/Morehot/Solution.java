import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 1, 2, 3, 9, 10, 12 }, 7));
    }
    public static int solution(int[] scoville, int K) {
        Queue<Integer> list = new PriorityQueue<>();
        int answer = 0;

        for (int i : scoville) {
            list.offer(i);
        }

        while (true) {
            if (list.peek() >= K) break;
            if (list.size() == 1) return -1;
            list.offer(list.poll() + (list.poll() * 2));
            answer++;
        }
        return answer;
    }
}