import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[][] { { 0,1,1 }, { 0,2,2 }, { 1,2,5 }, { 1,3,1 }, { 2,3,8 } }));
    }

    // 크루스칼 알고리즘을 이용하자 (https://kingpodo.tistory.com/51)
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        int[][] costList = new int[n][n];
        int minCost = costs[0][2];
        int minIslandFrom = costs[0][0];
        int minIslandTo = costs[0][1];

        List<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < n; ++i) 
            visited.add(false);

        for (int i = 0; i < costs.length; ++i) {
            costList[costs[i][0]][costs[i][1]] = costs[i][2];
            costList[costs[i][1]][costs[i][0]] = costs[i][2];

            // 크루스칼 알고리즘의 출발 지점을 찾기 위한 과정
            if (costs[i][2] < minCost) {
                minCost = costs[i][2];
                minIslandFrom = costs[i][0];
                minIslandTo = costs[i][1];
            }
        }

        while (visited.contains(false)) {
            answer += minCost;
            visited.set(minIslandFrom, true);
            visited.set(minIslandTo, true);
            minCost = (int)1e9; // 그냥 존재할리 없는 큰 수로 설정하기 위해 (1*10^9)

            for (int i = 0; i < n; ++i) {
                if (visited.get(i) == true) {
                    for (int j = 0; j < n; ++j) {
                        if (costList[i][j] != 0 && visited.get(j) == false && minCost > costList[i][j]) {
                            minCost = costList[i][j];
                            minIslandFrom = i;
                            minIslandTo = j;
                        }
                    }
                }
            }
        }

        return answer;
    }
}