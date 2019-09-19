import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 4, 1, 5, 2, 3 }, new int[] { 1, 3, 7, 9, 5 }));
    }

    /**
     * note1에 note2의 숫자가 있으면 1, 없으면 0을 출력
     * binarySearch이용
     * @param note1
     * @param note2
     * @return
     */
    public static String solution(int[] note1, int[] note2) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(note1);

        for (int i : note2) {
            sb.append(Arrays.binarySearch(note1, i) > -1 ? "1 " : "0 ");
        }

        return sb.toString();
    }

    /**
     * 이진 탐색 기본 알고리즘 코드
     * 기본적으로 Arrays내에 존재하므로 굳이 만들어 쓸 필요는 없다.
     * 그냥 개념 파악용
     * @param arr  전체 배열
     * @param target  찾고자 하는 타겟
     * @return  존재하면 해당 인덱스, 없으면 -1
     */
    public static int binarySearch(int[] arr, int target) {
        int first = 0;
        int last = arr.length - 1;
        int mid;

        while (first <= last) {
            mid = (first + last) / 2;

            if (target == arr[mid]) {
                return mid;
            } else {
                if (target < arr[mid]) {
                    last = mid - 1;
                } else {
                    first = mid + 1;
                }
            }
        }

        return -1;
    }


}