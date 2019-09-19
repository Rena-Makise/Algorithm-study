import java.util.*;

class Solution {
    static List<String> list = new ArrayList<>();
    static String route = "";
    static boolean[] visit;

    public static void main(String[] args) {
        String[] result1 = solution(new String[][] { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } });
        String[] result2 = solution(new String[][] { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" }, { "ATL", "SFO" } });

        for (String s : result1) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : result2) {
            System.out.print(s + " ");
        }
    }

    public static void dfs(String[][] tickets, String end, int cnt) {
        route += end + ",";

        if (cnt == tickets.length) {
            list.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String s = tickets[i][0];
            String e = tickets[i][1];
            if(s.equals(end) && !visit[i]) {
                visit[i] = true;
                dfs(tickets, e, cnt +1);
                // 지금까지의 경뢰 외에 중간에 다르나 경로로도 갈 가능성이 있으므로
                // dfs의 호출이 종료되면 visit과 route에서 현재 방문 위치를 빼 줘야 한다.
                visit[i] = false;
                route = route.substring(0, route.length() - 4); // 공항명 3글자 + 콤마 = 4
            }
        }
    }

    public static String[] solution(String[][] tickets) {
        for (int i = 0; i < tickets.length; ++i) {
            visit = new boolean[tickets.length];
            String start = tickets[i][0];
            String end = tickets[i][1];

            if (start.equals("ICN")) {
                route = start + ",";
                visit[i] = true;
                dfs(tickets, end, 1);
            }
        }

        Collections.sort(list);
        String[] answer = list.get(0).split(",");
        return answer;
    }
}