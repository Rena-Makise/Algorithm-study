import java.util.*;

class Node {
    int index;
    int weight;

    public Node (int index, int weight) {
        this.index = index;
        this.weight = weight;
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[][] { { 1,2,1 }, { 2,3,3 }, { 5,2,2 }, { 1,4,2 }, { 5,3,1 }, { 5,4,2 } }, 3));
        System.out.println(solution(6, new int[][] { { 1,2,1 }, { 1,3,2 }, { 2,3,2 }, { 3,4,3 }, { 3,5,2 }, { 3,5,3 }, { 5,6,1 } }, 4));
        System.out.println(solution2(5, new int[][] { { 1,2,1 }, { 2,3,3 }, { 5,2,2 }, { 1,4,2 }, { 5,3,1 }, { 5,4,2 } }, 3));
        System.out.println(solution2(6, new int[][] { { 1,2,1 }, { 1,3,2 }, { 2,3,2 }, { 3,4,3 }, { 3,5,2 }, { 3,5,3 }, { 5,6,1 } }, 4));
    }

    /**
     * 배달 - 다익스트라 알고리즘
     * 하나의 정점에서 다른 모든 정점까지의 최단 경로를 구하는 문제 해결법
     * 단, 다익스트라 알고리즘에서는 음의 가중치를 가진 간선은 쓸 수 없다!!!
     * https://hsp1116.tistory.com/42 참고
     * @param N  마을의 갯수
     * @param road  도로의 정보
     * @param K  음식 배달이 가능한 시간
     * @return
     */
    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);  // 처음에 dist 배열을 INF값으로 채워준다.

        // 그래프 제작
        List<Node>[] nodes = new ArrayList[N + 1];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; ++i) {
            nodes[road[i][0]].add(new Node(road[i][1], road[i][2]));
            nodes[road[i][1]].add(new Node(road[i][0], road[i][2]));
        }

        Queue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        dist[1] = 0;  // 시작정점의 최단 경로는 0이다.
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node c = pq.poll();

            // 현재 정점까지의 비용이 이미 구한 비용보다 크다면 무시
            if(c.weight > dist[c.index])
                continue;
        
            // 현재 정점과 연결된 정점들을 탐색한다.
            for (Node n : nodes[c.index]) {
                // (현재 정점까지 온 비용 + 다음 정점까지 가는 비용) < 이미 구한 다음 정점까지의 비용이면,
                if (dist[c.index] + n.weight < dist[n.index]) {
                    dist[n.index] = dist[c.index] + n.weight;   // 다음 정점까지의 비용을 갱신하고
                    pq.add(new Node(n.index, dist[c.index] + n.weight));  // 큐에 삽입

                }
            }   
        }

        // 1부터 다른 마을까지의 비용 중 K보다 작게 걸린다면 배달 가능!
        for (int i = 1; i < dist.length; ++i) {
            if (dist[i] <= K)
                answer++;
        }
        
        return answer;
    }


    /**
     * 배달 - 플로이드-워셜 알고리즘
     * 모든 최단 경로를 구하는 문제에서 사용
     * 다익스트라와는 다르게 음의 가중치를 가진 간선도 쓸 수 있다.
     * https://hsp1116.tistory.com/45?category=547783 참고
     * @param N  마을의 갯수
     * @param road  도로의 정보
     * @param K  음식 배달이 가능한 시간
     * @return
     */
    public static int solution2(int N, int[][] road, int K) {
        int answer = 0;

        // 거리를 저장한 테이블
        int[][] mapD = new int[N + 1][N + 1];

        for (int i = 0; i < mapD.length; ++i) {
            Arrays.fill(mapD[i], 987654321); // 도달 못함(INF)으로 초기화. 더했을 때 오버플로우가 나지 않을 정도의 수로
            mapD[i][i] = 0; // 자기 자신의 비용은 0
        }

        // 그래프 제작
        for (int i = 0; i < road.length; ++i) {
            // a<->b인 경로가 여러개 존재하면 가장 비용이 낮은 경로로 설정해준다.
            if (mapD[road[i][0]][road[i][1]] > road[i][2]) {
                mapD[road[i][0]][road[i][1]] = road[i][2];
                mapD[road[i][1]][road[i][0]] = road[i][2];
            }
        }

        // 중간경로 추가
        for (int k = 1; k < N + 1; ++k) {
            for (int i = 1; i < N + 1; ++i) {
                for (int j = 1; j < N + 1; ++j) {
                    if (mapD[i][j] > mapD[i][k] + mapD[k][j]) {
                        mapD[i][j] = mapD[i][k] + mapD[k][j];
                    }
                }
            }
        }

        // 배달 가능한 지역 탐색
        for (int i = 1; i < N + 1; ++i) {
            if (mapD[1][i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}