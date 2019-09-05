import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] answer1 = solution("KAKAO");
        for (int i = 0; i < answer1.length; ++i) 
            System.out.print(answer1[i] + " ");

        System.out.println();

        int[] answer2 = solution("TOBEORNOTTOBEORTOBEORNOT");
        for (int i = 0; i < answer2.length; ++i) 
            System.out.print(answer2[i] + " ");
            
        System.out.println();

        int[] answer3 = solution("ABABABABABABABAB");
        for (int i = 0; i < answer3.length; ++i) 
            System.out.print(answer3[i] + " ");
    }

    public static int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();
        char c = 'A';
        int n = 1;
        while (map.size() < 26) {
            map.put(String.valueOf(c++), n++);
        }

        ArrayList<Integer> idxList = new ArrayList<>();
        while (!msg.isEmpty()) {
            for (int i = msg.length(); i > 0; --i) {
                if (map.containsKey(msg.substring(0, i))) {
                    String key = msg.substring(0, i);
                    String nextKey = msg.length() > i+1 ? msg.substring(i, i + 1) : "";
                    idxList.add(map.get(key));
                    if (!nextKey.isEmpty() && !map.containsKey(key + nextKey)) {
                        map.put(key + nextKey, Integer.valueOf(map.size() + 1));
                    }
                    msg = msg.substring(key.length());
                    break;
                }
            }
        }

        int[] answer = new int[idxList.size()];
        for (int i = 0; i < idxList.size(); ++i) {
            answer[i] = idxList.get(i);
        }
        return answer;
    }
  }