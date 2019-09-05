import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{ 3, 0, 6, 1, 5 }));
    }

    // h번 이상 인용된 논문이 h편 이상인 h의 최댓값;;;
    public static int solution(int[] citations) {
        List<Integer> list = new ArrayList<>();
        int answer = 0; 
        for (int i : citations) {
            list.add(i);
        }
        Collections.sort(list);
        Collections.reverse(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < i + 1)
                return i;   
        }
        return citations.length;
    }
}