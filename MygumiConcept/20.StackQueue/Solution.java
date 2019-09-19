import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("A*(B+C)"));
    }
    
    /**
     * 후위 표기법
     * 스택을 활용
     * @param s  중위 표기법으로 구성된 식
     * @return  후위 표기법으로 변환하여 리턴
     */
    public static String solution(String s) {
        char[] c = s.toCharArray();
        int len = c.length;

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        // 스택의 top보다 다음 연산자의 우선순위가 클 경우 push, 그렇지 않을 경우 pop
        // 우선순위가 같다면, 먼저 스택에 들어온 연산자를 먼저 내보내야 하기에 클 경우만 push
        // 괄호는 우선적으로 처리해줘야 하므로 괄호가 닫히면 이전 괄호까지 pop
        for (int i = 0; i < len; ++i) {
            int p = priority(c[i]);
            char ch = c[i];

            switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && priority(stack.peek()) >= p) {
                        sb.append(stack.pop());
                    }
                    stack.push(ch);
                    break;
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(ch);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    public static int priority(char ch) {
        switch (ch) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            case '(':
            case ')':
                return 0;
        }
        return -1;
    }
}