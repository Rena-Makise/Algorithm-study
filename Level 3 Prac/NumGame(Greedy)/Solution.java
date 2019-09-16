import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 5,1,3,7 }, new int[] { 2,2,6,8 }));    
        System.out.println(solution(new int[] { 2,2,2,2 }, new int[] { 1,1,1,1 }));
    }

    public static int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int a = A.length - 1;
        int b = B.length - 1;
        while (a >= 0) {
            if (B[b] > A[a]) {
                answer++;
                b--;
            }
            a--;
        }
        return answer;
    }
}