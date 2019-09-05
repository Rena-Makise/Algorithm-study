import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
    }

    public static String solution(String number, int k) {
        int idx = 0;
        char max;
        // 그냥 String으로 하면 시간초과가 발생할 수도 있으므로....
        StringBuilder answer = new StringBuilder();

        // 입력된 숫자가 모두 0일 경우 예외처리
        if (number.charAt(0) == '0') return "0";

        // 각 자리 숫자를 뽑을 때 그때의 최댓값을 선택해야 한다.
        // 인덱스가 0부터 시작할때, k 개의 숫자를 뺐을 때 최대 숫자는 
        // 적어도 0번째부터 k번째 숫자 중에서 나와야 한다.
        // 그래야 총 k개의 수를 차례대로 제거할 수 있다.
        for (int i = 0; i < number.length() - k; i++) {
            max = '0';
            for (int j = idx; j <= k + i; j++) {
                if (max < number.charAt(j)) {
                    max = number.charAt(j); 
                    idx = j + 1;
                }
            }			
            answer.append(max);
        }
        return answer.toString();
    }
}