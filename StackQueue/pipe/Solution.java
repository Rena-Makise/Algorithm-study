import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("()(((()())(())()))(())"));
    }
    public static int solution(String arrangement) {
        List<Character> arr = new ArrayList<>();
        List<Character> stack = new ArrayList<>();
        int answer = 0;
        String arrCopy = arrangement.replace("()", "0");

        for (int i = 0; i < arrCopy.length(); i++) {
            arr.add(arrCopy.charAt(i));
        }

        // 핵심 아이디어
        // 레이저를 만나기 전의 '(' 갯수 = 토막난 막대기 수
        // ')'를 만나면 막대기 끝부분을 더해줘야 한다! +1
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == '0') {
                answer += stack.size();
            } else if (arr.get(i) == '(') {
                stack.add(arr.get(i));
            } else if (arr.get(i) == ')') {
                stack.remove(stack.size() - 1);
                answer += 1;
            }
        }

        return answer;
    }
}