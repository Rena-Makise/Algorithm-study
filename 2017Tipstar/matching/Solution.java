import java.util.*;

class Solution
{
    public static void main(String[] args) {
        System.out.println("결과1 : " + solution("baabaa"));
        System.out.println("결과2 : " + solution("cdcd"));
    }

    public static int solution(String s)
    {
        Deque<Character> deq = new ArrayDeque<Character>();

        for (char c : s.toCharArray())
        {
            if (!deq.isEmpty() && deq.peekFirst() == c) 
                deq.pollFirst();
            else
                deq.offerFirst(c);
        }

        return deq.isEmpty() ? 1 : 0;
    }
}