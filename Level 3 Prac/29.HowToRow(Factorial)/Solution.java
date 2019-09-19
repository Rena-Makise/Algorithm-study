import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] result = solution(3, 5);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    // 참고링크
    // http://blog.naver.com/PostView.nhn?blogId=jwyoon25&logNo=221347789536&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=1
    public static int[] solution(int n, long k) {
        List<Integer> people = new ArrayList<>();
        int[] answer = new int[n];
        int index = 0;
        long fac = 1;

        for (int i = 1; i <= n; ++i) {
            fac *= i;
            people.add(i);
        }

        k--; // index는 0부터 시작하니까....

        while (n > 0) {
            fac /= n--;
            answer[index] = people.get((int)(k / fac));
            people.remove((int)(k / fac));
            k %= fac;
            index++;
        }

        return answer;
    }
  }