import java.util.*;

/**
 * 하나의 정점에서 다른 모든 정점까지의 최단 경로를 구하는 문제의 경우
 * 다익스트라 알고리즘을 활용할 수 있다.
 * https://hsp1116.tistory.com/42 참고
 */
class Solution {
    static List<Integer>[] list;  // 그래프를 나타내는 list
    static int[] dist;  // 다익스트라 알고리즘에서의 dlist배열
    static int max;

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } }));
    }

    /**
     *  우선순위 큐(힙구조)를 이용한 다익스트라 알고리즘
     * @param n
     */
    public static void path (int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        dist[0] = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (Integer i : list[vertex]) {
                if (dist[i] > dist[vertex] + 1 && dist[i] == 9999) {
                    dist[i] = dist[vertex] + 1;
                    queue.add(i);
                    max = Math.max(max, dist[i]);
                }
            }
        }
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        list = new ArrayList[n];

        for (int i = 0; i < n; ++i) list[i] = new ArrayList<>();
        for (int i = 0; i < edge.length; ++i) {
            list[edge[i][0] - 1].add(edge[i][1] - 1); // 인덱스가 0부터 시작하니까.... -1
            list[edge[i][1] - 1].add(edge[i][0] - 1); // 인덱스가 0부터 시작하니까.... -1
        }
        dist = new int[n];
        Arrays.fill(dist, 9999);  // INF값으로 9999를 채워준다
        path(n);
        for (int i = 0; i < dist.length; i++) {
            if (max == dist[i]) answer++;
        }
        return answer;
    }
}