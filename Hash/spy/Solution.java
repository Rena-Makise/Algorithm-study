import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new String[][] { { "yellow_hat", "headgear" }, { "blue_sunglasses", "eyewear" }, { "green_turban", "headgear" }}));
        System.out.println(solution(new String[][] { { "crow_mask", "face" }, { "blue_sunglasses", "face" }, { "smoky_makeup", "face" } }));
    }

    // 개념 : a, b, c개의 옷이 존재한다면 (a + 1) * (b + 1) * (c + 1)
    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        int answer = 0;

        for (String[] cloth : clothes) {
            if (map.get(cloth[1]) != null) {
                int value = map.get(cloth[1]) + 1;
                map.put(cloth[1], value);
            } else {
                map.put(cloth[1], 1);
            }
        }

        Set<String> ks = map.keySet();
        int temp = 1;
        for (String s : ks) {
            temp *= map.get(s) + 1;
        }
        answer += temp;

        // 모두 안입는 경우 제외
        return answer - 1;
    }
}