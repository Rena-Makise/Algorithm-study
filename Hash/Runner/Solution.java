import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"leo", "kiki", "eden"}, new String[] {"eden", "kiki"}));
    }

    public static String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        String answer = "";

        for (String person : participant) {
            if (map.get(person) == null) {
                map.put(person, 1);
            } else {
                int val = map.get(person);
                map.put(person, val + 1);
            }
        }

        for (String person : completion) {
            if (map.get(person) != null) {
                int value = map.get(person);
                map.put(person, value - 1);
            }
        }

        Set<String> ks = map.keySet();

        for (String s : ks) {
            if (map.get(s) != 0)
                answer = s;
        }

        return answer;
    }
}