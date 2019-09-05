import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("JEROEN"));
        System.out.println(solution("JAN"));
    }

    public static int solution(String name) {
        int answer = 0;
        int reg = name.length() - 1; // 일반적으로는 문자의 마지막까지 커서를 옮긴다.
        for (int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            // A를 0으로 두고 순방향으로 카운트했을때 N은 13이다.
            // Z를 1로 두고 역박향으로 카운트했을 때 역시 N은 13이다.
            // 조이스틱을 위로 올리는 것과 아래로 내리는 것 중 작은 값을 구한다.
            answer += ('Z' - c + 1) > c - 'A' ? c - 'A' : ('Z' - c + 1);
            if (c == 'A') {
                int nextIdx = i + 1;
                int countA = 0;
                while (nextIdx < name.length() && name.charAt(nextIdx) == 'A') {
                    countA++;
                    nextIdx++;
                }

                // A가 있으면 순방향으로 나아가는것과 역방향으로 가는것 중
                // 어느것이 더 적게 걸리는지 계산
                // A를 만나면 왔던 길을 다시 되돌아가기에 x 2
                // 그리고 A의 갯수만큼 갈 필요가 없으므로 - countA
                int temp = (i - 1) * 2 + (name.length() - 1 - i - countA);
                // 역방향으로 가는게 더 적게 걸리면 해당 값으로 세팅
                if (reg > temp) reg = temp;
            }
        }
        
        answer += reg;
        return answer;
    }
}




// int lcnt = 0; // 커서를 왼쪽으로 이동시켰을 때 조작횟수
// int rcnt = 0; // 커서를 오른쪽으로 이동시켰을 때 조작횟수
// int idx = 0;
// char[] larr = new char[name.length()];
// char[] rarr = new char[name.length()];
// Arrays.fill(larr, 'A');
// Arrays.fill(rarr, 'A');

// if (name.charAt(0) != 'A') {
//     // A를 0으로 두고 순방향으로 카운트했을때 N은 13이다.
//     // Z를 1로 두고 역박향으로 카운트했을 때 역시 N은 13이다.
//     // 따라서 특정 문자가 13보다 큰지 작은지를 파악해서 
//     // 조이스틱을 위로 올리는 것과 아래로 내리는 것 중 작은 값을 구한다.
//     int cnt = (name.charAt(0) - 'A' <= 13 ? name.charAt(0) - 'A' : 91 - name.charAt(0));

//     rarr[0] = name.charAt(0);
//     larr[0] = naem.charAt(0);
//     idx = 1;
// }
