import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(2, 4, 2, 1));
        System.out.println(solution(16, 16, 2, 1));
        System.out.println(solution(16, 16, 2, 2));
    }

    static String conversion(int n, int num) {
        String retVel = "";
        if (num == 0) retVel = "0";
        while (num != 0) {
            retVel = (num % n < 10 ? Integer.toString(num % n) : Character.toString((char)(num % n + 55))) + retVel;
            num = num / n;
        }
        return retVel;
    }

    /**
     * 진법 구하기
     * @param n  진법
     * @param t   미리 구할 숫자의 갯수
     * @param m  게임에 참가하는 인원
     * @param p  튜브의 순서
     * @return
     */
    public static String solution(int n, int t, int m, int p) {
        int num = 0;
        int turn = 0;
        String answer = "";
        String convNum = "";

        while (answer.length() < t) {
            convNum = conversion(n, num++);
            for (char c : convNum.toCharArray()) {
                turn++;
                if (turn == p) {
                    answer += c;
                    if (answer.length() == t)
                        break;
                }
                if (turn == m)
                    turn = 0;
            }
        }

        return answer;
    }
  }