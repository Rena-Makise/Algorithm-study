import java.util.*;

class RankNode {
    int key;
    HashSet<Integer> win;  // HashSet은 순서없고 중복없는 집합
    HashSet<Integer> lose;
    boolean isRank;
    
    public RankNode(int k) {
        key = k;
        win = new HashSet<>();
        lose = new HashSet<>();
        isRank = false;
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[][] { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } }));    
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;
        List<RankNode> list = new ArrayList<>();
        list.add(new RankNode(0));
        for (int i = 1; i <= n; ++i) {
            list.add(new RankNode(i));
        }

        for (int[] result : results) {
            list.get(result[0]).win.add(result[1]);
            list.get(result[1]).lose.add(result[0]);
        }

        for (int k = 0; k < 2; ++k) {
            for (int i = 1; i <= n; ++i) {
                RankNode rn = list.get(i);
                HashSet<Integer> temp = new HashSet<>();
                for (Integer win : rn.win) {
                    for (Integer otherwin : list.get(win).win) {
                        temp.add(otherwin);
                    }
                }
                rn.win.addAll(temp);
                temp = new HashSet<>();
                for (Integer lose : rn.lose) {
                    for (Integer otherlose : list.get(lose).lose) {
                        temp.add(otherlose);
                    }
                }
                rn.lose.addAll(temp);
            }
        }
        for (RankNode rn : list) {
            int size = rn.win.size();
            size += rn.lose.size();
            if (size >= n - 1) {
                answer++;
            }
        }
        return answer;
    }
}