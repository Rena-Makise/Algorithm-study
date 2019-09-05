import java.util.*;

class Solution {
    static final int HIT = 1;
    static final int MISS = 5;

    public static void main(String[] args) {
        System.out.println(solution(3, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo",
                "Seoul", "NewYork", "LA" })); // 50
        System.out.println(solution(3,
                new String[] { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" })); // 21
        System.out.println(solution(2, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco",
                "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" })); // 60
        System.out.println(solution(5, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco",
                "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" })); // 52
        System.out.println(solution(2, new String[] { "Jeju", "Pangyo", "NewYork", "newyork" })); // 16
        System.out.println(solution(0, new String[] { "Jeju", "Pangyo", "Seoul", "NewYork", "LA" })); // 25

    }

    public static int solution(int cacheSize, String[] cities) {
        LinkedList<String> cache = new LinkedList<String>();
        int answer = 0;

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();

            if (cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                answer += HIT;
            } else {
                if(cache.size() < cacheSize) {
                    cache.add(city);
                } else if (cacheSize > 0) {
                    cache.removeFirst();
                    cache.add(city);
                }
                answer += MISS;
            }
        }
        return answer;
    }
}