import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(1, 13, "OOIOIOIOIIOII"));
    }

    /**
     * IOIOI 문제 - KMP 알고리즘
     * @param n  n + 1개의 I와 n개의 O로 이루어진 패턴
     * @param m  s의 길이
     * @param s  전체 문자열
     * @return
     */
    public static int solution(int n, int m, String s) {
        int result = 0;

        StringBuilder pattern = new StringBuilder("IOI");
        for (int i = 1; i < n; ++i) {
            pattern.append("OI");
        }

        ArrayList<Integer> list = kmp(s, pattern.toString());

        result = list.size();

        return result;
    }

    /**
     * Pi 배열
     * @param pattern 찾을 문자열
     * @return
     */
    public static int[] getPi(String pattern) {
        int m = pattern.length();
        int j = 0;
        char[] p = new char[m];
        int[] pi = new int[m];

        p = pattern.toCharArray();

        for (int i = 1; i < m; ++i) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }

            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
    
    /**
     * kmp 알고리즘
     * https://bowbowbow.tistory.com/6#comment5168448 참고
     * @param str  전체 문자열
     * @param pattern  찾을 문자열
     * @return
     */
    public static ArrayList<Integer> kmp(String str, String pattern) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] pi = getPi(pattern);
        int n = str.length();
        int m = pattern.length();
        int j = 0;
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        
        // i - 전체 문자열과 비교할 인덱스. 1씩 증가한다.
        // j - 찾을 문자열의 비교 인덱스
        for (int i = 0; i < n; ++i) {
            while (j > 0 && s[i] != p[j]) {
                // 중간 단계 뛰어넘기
                // pi배열을 이용하여 j인덱스를 변경시킴으로써 while문 중단
                j = pi[j - 1];
            }

            if (s[i] == p[j]) {
                if (j == m - 1) {
                    // j는 비교 인덱스로써, 인덱스가 찾을 문자열의 크기에 도달하면 문자열 찾음
                    // 찾을 문자열의 첫 번째 인덱스 번호를 리스트에 추가
                    list.add(i - m + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        return list;
    }
}