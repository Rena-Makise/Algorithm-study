import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("17"));   
        System.out.println(solution("011"));
    }

    /**
     * 순열을 구하는 로직 프로세스 (https://gorakgarak.tistory.com/522 참고)
     * @param arr  계속해서 데이터를 들고다니면서 교횐되고 있는 배열
     * @param depth  현재 트리구조에서 어떤 깊이에서 교환작업을 하고 있는지에 대한 변수
     * @param k  총 배열안에 들어있는 숫자 갯수
     * @param sosuList  숫자 중 소수인 수를 담을 셋
     */
    public static void perm(char[] arr, int depth, int k, Set sosuList) {
        // 한번 depth가 k로 도달하면 사이클이 돌았으므로 출력
        if (depth == k) {
            printPerm(arr, k, sosuList);
            return;
        } else {
            for (int i = depth; i < arr.length; ++i) {
                swap(arr, i, depth);
                perm(arr, depth + 1, k, sosuList);
                swap(arr, i, depth);
            }
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printPerm(char[] arr, int k, Set sosuList) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < k; ++i) {
            //System.out.print(arr[i]);
            a.append(arr[i]);
        }
        //System.out.println();
        isSosu(sosuList, a);
    }

    /**
     * 소수를 구하는 로직 (https://jar100.tistory.com/16 참고)
     * 소수는 자기자신과 1로만 나눠져야 한다.
     * 따라서 2부터 자기자신까지 나누어보고 나누어 떨어지는 것이 없으면 소수
     * 하지만 자기자신 n까지 나누지 않고 제곱근(Math.sqrt) n까지 나누어도 된다!
     */
    public static void isSosu(Set sosuList, StringBuilder sb) {
        int b = Integer.parseInt(String.valueOf(sb));
        boolean sosu = true;
        if (b <= 1)
            return;
        for (int j = 2; j <= Math.sqrt(b); ++j) {
            if (b % j == 0) {
                sosu = false;
                break;
            }
        }

        if (sosu)
            sosuList.add(b);
    }
    
    public static int solution(String numbers) {
        char[] list = numbers.toCharArray();
        Set<Integer> sosuList = new HashSet<>();

        for (int i = 1; i <= list.length; ++i) {
            perm(list, 0, i, sosuList);
        }

        return sosuList.size();
    }
}