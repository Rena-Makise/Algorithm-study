import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[] { 2, 4 }, new int[] { 1, 3, 5 }));
        System.out.println(solution(5, new int[] { 2, 4 }, new int[] { 3 }));
        System.out.println(solution(3, new int[] { 3 }, new int[] { 1 }));
    }

    // 전체 학생 배열을 만들어 가지고 있는 체육복의 수를 넣어준다.
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int lostNum = 0;
        
        int[] people = new int[n];
        Arrays.fill(people, 1);

        for (int i = 0; i < reserve.length; ++i) {
            people[reserve[i] - 1] += 1;
        }

        for (int i = 0; i < lost.length; ++i) {
            people[lost[i] - 1] -= 1;
            if (people[lost[i] - 1] == 0) lostNum++;
        }

        for (int i = 0; i < people.length; ++i) {
            if (people[i] == 0) {
                if (i != 0 && people[i - 1] == 2) {
                    people[i] += 1;
                    people[i - 1] -= 1;
                    lostNum--;
                } else if (i != people.length - 1 && people[i + 1] == 2) {
                    people[i] += 1;
                    people[i + 1] -= 1;
                    lostNum--;
                }
            }
        }

        answer = n - lostNum;

        return answer;
    }
}