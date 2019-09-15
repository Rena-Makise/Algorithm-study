import java.util.*;

class Solution {
    static boolean[] v;  // 해당 단어를 탐색하였는지 체킹하는 배열
    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
    }

    /**
     * 깊이/너비 우선 탐색을 이용한 단어 탐색
     * @param begin  시작 단어
     * @param target  타겟 단어
     * @param words  워드 배열
     * @return
     */
    public static int dfs(String begin, String target, String[] words) {
        int answer = 0;
        String dupBegin;

        for (int i = 0; i < words.length; i++) {
            if (v[i]) continue;

            for (int j = 0; j < begin.length(); j++) {
                dupBegin = begin;
                dupBegin = (j > 0 ? begin.substring(0, j) : "") + words[i].charAt(j) + (j < begin.length() - 1 ? begin.substring(j + 1) : "");

                if (dupBegin.equals(target))
                    return 1;
                if (dupBegin.equals(words[i])) {
                    v[i] = true;
                    answer = 1;
                    answer += dfs(dupBegin, target, words);
                }
            }
        }

        return answer;
    }

    public static int solution(String begin, String target, String[] words) {
        v = new boolean[words.length + 1];
        for (int i = 0; i < words.length; ++i) {
            if (target.equals(words[i])) {
                return dfs(begin, target, words);
            }
        }

        return 0;
    }
}