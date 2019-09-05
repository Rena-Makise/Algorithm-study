import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 70, 50, 80, 50 }, 100));
        System.out.println(solution(new int[] { 70, 80, 50 }, 100));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        int j = 0;
        Arrays.sort(people);

        // 최대한 적은 횟수로 탈출하기 위해서는 "최대 + 최소"로 짝을 지어야 한다
        for (int i = people.length - 1; i > j; --i) {
            if (people[i] + people[j] <= limit) {
                answer++;
                j++;
            }
        }

        return people.length - answer;
    }
}