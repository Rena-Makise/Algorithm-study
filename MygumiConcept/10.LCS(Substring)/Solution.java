import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("ABRACADABRA", "ECADADABRBCRDARA"));
    }

    /**
     * 두 문자열을 비교했을 때 
     * 두 문자열에 모두 포함된 가장 긴 공통 부분 문자열의 길이를 출력
     * LCS Longest Common Substring 이용
     * @param a  문자열 1
     * @param b  문자열 2
     * @return
     */
    public static int solution(String a, String b) {
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int[][] lcs = new int[A.length + 1][B.length + 1];
        int answer = 0;

        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= B.length; ++j) {
                if(A[i - 1] == B[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;

                    if (answer < lcs[i][j])
                        answer = lcs[i][j];
                }
            }
        }
        return answer;
    }
}