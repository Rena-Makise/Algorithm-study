import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[] { 4, 3, 3 }));
        System.out.println(solution(1, new int[] { 2, 1, 2 }));
        System.out.println(solution(3, new int[] { 1, 1 }));
    }

    public static long solution(int n, int[] works) {
        // 우선순위 큐를 사용하면 정렬 시간을 훨씬 줄일 수 있다!
        Queue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;

        for (int i : works) {
            que.offer(i);
        }

        for (int i = 0; i < n; ++i) {
            int temp = que.poll();
            if (temp == 0) return 0;

            que.offer(temp - 1);
        }
        

        while (!que.isEmpty()) {
            answer += (long)Math.pow(que.poll(), 2);
        }

        return answer;
    }
}