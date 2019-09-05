import java.util.*;

class Solution {
    static boolean[] v;
    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
    }

    public static int dfs(String begin, String target, String[] words) {
        int answer = 0;
        String dupBegin;

        for (int i = 0; i < words.length; i++) {
            if (v[i]) continue;

            for (int j = 0; j < begin.length(); j++) {
                dupBegin = begin;
                // string.replace를 사용할 경우에는 해당하는 문자 전부가 바뀌어 버린다...  따라서 substring으로 일일히 잘라서 갖다붙임.
                dupBegin = (j > 0 ? begin.substring(0, j) : "") + words[i].charAt(j) + (j < begin.length() - 1 ? begin.substring(j + 1) : "");

                if (dupBegin.equals(target))
                    return 1; // 현재 단어 하나를 선택했으므로
                if (dupBegin.equals(words[i])) {
                    v[i] = true;
                    answer = 1; // 현재 단어 하나를 선택했으므로
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