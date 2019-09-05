import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[] arr1;
        int[] arr2;
        
        arr1 = new int[]{9, 20, 28, 18, 11};
        arr2 = new int[]{30, 1, 21, 17, 28};
        String[] answer1 = solution(5, arr1, arr2);
        for (int i = 0; i < answer1.length; ++i) {
            System.out.println(answer1[i]);
        }
        
        System.out.println();

        arr1 = new int[]{46, 33, 33 ,22, 31, 50};
        arr2 = new int[]{27 ,56, 19, 14, 14, 10};
        String[] answer2 = solution(6, arr1, arr2);
        for (int i = 0; i < answer2.length; ++i) {
            System.out.println(answer2[i]);
        }
    }
    
    public static String convert (int num, int n) {
        StringBuilder sb = new StringBuilder();
        char[] binaryStr = Integer.toBinaryString(num).toCharArray();

        int length = n - binaryStr.length;
        for (int i = 0; i < length; ++i) {
            sb.append(" ");
        }

        for (char c : binaryStr) {
            if (c == '1') sb.append("#");
            else if (c == '0') sb.append(" ");
        }

        return sb.toString();
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; ++i) {
            answer[i] = convert(arr1[i] | arr2[i], n);
        }

        return answer;
    }
  }