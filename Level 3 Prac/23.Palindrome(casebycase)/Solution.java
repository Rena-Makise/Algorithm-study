import java.util.*;

class Solution
{
    public static void main(String[] args) {
        System.out.println(solution("abcdcba"));
        System.out.println(solution("abacde"));;
        System.out.println(solution("eabccbaacde"));
    }
    public static int solution(String s)
    {
        int answer = 0;
        int max = 1;
        char[] stringChar = s.toCharArray();

        for (int i = 0; i < s.length(); ++i) {
            if (i > 0 && stringChar[i] == stringChar[i - 1]) {
                int j = 1;
                while (true) {
                    if (i - j - 1 < 0 || i + j >= s.length()) {
                        break;
                    } else if (stringChar[i + j] != stringChar[i - j - 1]) {
                        break;
                    }
                    j++;
                }
                if (max < j * 2) {
                    max = j * 2;
                } 
            }
            if (i > 1 && stringChar[i] == stringChar[i - 2]) {
                int j = 1;
                while (true) {
                    if (i - j - 2 < 0 || i + j >= s.length()) {
                        break;
                    } else if (stringChar[i + j] != stringChar[i - j - 2]) {
                        break;
                    }
                    j++;
                }
                if (max < (j) * 2 + 1) {
                    max = (j) * 2 + 1;
                } 
            }
        }

        answer = max;
        return answer;
    }
}