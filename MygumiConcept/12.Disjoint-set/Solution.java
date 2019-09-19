import java.util.*;

class Solution {
    static final int MAX =1000001;
    static int[] parent = new int[MAX];
    public static void main(String[] args) {
        String[] result = solution(7, 8, new int[][] { { 0, 1, 3 }, { 1, 1, 7 }, { 0, 7, 6 }, { 1, 7, 1 }, { 0, 3, 7 }, { 0, 4, 2 }, { 0, 1, 1 }, { 1, 1, 1 } });

        for (String s : result) {
            System.out.println(s);
        }
    }

    /**
     * 집합의 표현
     * 합집합은 0 a b
     * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b
     * 확인 결과를 출력
     * @param n  초기의 {0}, {1}, {2}, ... {n} 이 각각 n+1개 집합
     * @param m  m개의 연산
     * @param arr  연산
     * @return
     */
    public static String[] solution(int n, int m, int[][] arr) {
        List<String> list = new ArrayList<>();

        for (int i = 1; i < MAX; ++i) {
            parent[i] = i;
        }
        

        for (int i = 0; i < m; ++i) {
            int k = arr[i][0];
            int a = arr[i][1];
            int b = arr[i][2];

            if (k == 1) {
                a = find(a);
                b = find(b);
                if (a == b) {
                    list.add("YES");
                } else {
                    list.add("NO");
                }
            } else {
                merge(a, b);
            }
        }
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    /**
     * 파인드(Find)
     * 어떤 원소가 주어졌을 때 이 원소가 속한 집합을 반환
     * @param u  원소
     * @return
     */
    public static int find(int u) {
        if (u == parent[u]) {
            return u;
        }
        return parent[u] = find(parent[u]);
    }

    /**
     * 유니온(Union)
     * 두 개의 집합을 하나의 집합으로 합친다.
     * @param u  집합 1
     * @param v  집합 2
     */
    public static void merge(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v)
            return;
        
        parent[u] = v;
    }
}