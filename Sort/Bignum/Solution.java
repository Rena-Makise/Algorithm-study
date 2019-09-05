import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 6, 10, 2 }));
        System.out.println(solution(new int[] { 3, 30, 34, 5, 9 }));
    }

    public static String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        String answer = "";
        
        for (int i : numbers) {
            list.add(Integer.toString(i));
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(Integer.valueOf(o1 + o2) > Integer.valueOf(o2 + o1))
                    return -1;
                else if (Integer.valueOf(o1 + o2) == Integer.valueOf(o2 + o1))
                    return 0;
                else
                    return 1;
            }
        });

        if (list.get(0).equals("0")) return "0";

        for (String s : list) {
            answer += s;
        }

        return answer;
    }
}