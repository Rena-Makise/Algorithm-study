import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(getLCSLength("ACAYKP", "CAPCAK"));
        System.out.println(getLCS("ACAYKP", "CAPCAK"));
        System.out.println(getThreeLCSLength("abcdefghijklmn", "bdefg", "efg"));
    }

    /**
     * 두 문자열이 주어졌을 때
     * 모두의 부분 문자열이 되는 문자열 중 가장 긴 것의 길이를 출력
     * LCS Longest Common Subsequence 사용
     * @param a  문자열 1
     * @param b  문자열 2
     * @return
     */
    public static int getLCSLength(String a, String b) {
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int[][] lcs = new int[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= B.length; ++j) {
                if (A[i - 1] == B[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
                }
            }
        }

        return lcs[A.length][B.length];
    }

    /**
     * 두 문자열이 주어졌을때
     * 모두의 부분 문자열이 되는 문자열 LCS를 출력
     * LCS Longest Common Subsequence
     * @param a  문자열 1
     * @param b  문자열 2
     * @return
     */
    public static String getLCS(String a, String b) {
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        StringBuilder sb = new StringBuilder();

        int[][] lcs = new int[A.length + 1][B.length + 1];
        String[][] solution = new String[A.length + 1][B.length + 1];

        // LCS에 해당하는 부분은 대각선으로 이동하는 시점의 문자를 뽑아내면 된다!
        // https://mygumi.tistory.com/126?category=677288 참고
        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= B.length; ++j) {
                if (A[i - 1] == B[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;

                    solution[i][j] = "diagonal";  // 대각선
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);

                    if (lcs[i][j] == lcs[i - 1][j]) {
                        solution[i][j] = "top";  // 위
                    } else {
                        solution[i][j] = "left";  // 왼쪽
                    }
                }
            }
        }

        int aLenght = A.length;
        int bLenght = B.length;

        while (solution[aLenght][bLenght] != null) {
            if (solution[aLenght][bLenght] == "diagonal") {
                sb.append(A[aLenght - 1]);
                aLenght--;
                bLenght--;
            } else if (solution[aLenght][bLenght] == "top") {
                aLenght--;
            } else if (solution[aLenght][bLenght] == "left") {
                bLenght--;
            }
        }

        return sb.reverse().toString();
    }

    /**
     * 세 개의 문자열이 주어졌을 때
     * 모두의 부분 문자열이 되는 문자열 중 가장 긴 것의 길이를 출력
     * LCS Longest Common Subsequence 사용
     * @param a  문자열 1
     * @param b  문자열 2
     * @param c  문자열 3
     * @return
     */
    public static int getThreeLCSLength(String a, String b, String c) {
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        char[] C = c.toCharArray();

        int[][][] lcs = new int[A.length + 1][B.length + 1][C.length + 1];
        
        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= B.length; ++j) {
                for (int k = 1; k <= C.length; ++k) {
                    if (A[i - 1] == B[j - 1] && B[j - 1] == C[k - 1]) {
                        lcs[i][j][k] = lcs[i - 1][j - 1][k - 1] + 1;
                    } else {
                        lcs[i][j][k] = Math.max(Math.max(lcs[i - 1][j][k], lcs[i][j - 1][k]), lcs[i][j][k - 1]);
                    }
                }
            }
        }

        return lcs[A.length][B.length][C.length];
    }
}