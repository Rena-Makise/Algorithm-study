import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new String[] { "go", "gone", "guild" }));
        System.out.println(solution(new String[] { "abc", "def", "ghi", "jklm" }));
        System.out.println(solution(new String[] { "word", "war", "warrior", "world" }));
    }
    
    static class Node {
        char c;
        int cnt;
        HashMap<Character, Node> map;

        public Node(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
            map = new HashMap<>();
        }
    }

    public static int solution(String[] words) {
        int len = words.length;
        int answer = 0;
        HashMap<Character, Node> map = new HashMap<>();
        Node current;

        for (int i = 0; i < len; ++i) {
            String str = words[i];
            current = null;

            for (int j = 0; j < str.length(); ++j) {
                char c = str.charAt(j);

                if (current != null) {
                    if (current.map.containsKey(c)) {
                        current = current.map.get(c);
                        current.cnt++;
                    } else {
                        Node node = new Node(c, 1);
                        current.map.put(c, node);
                        current = node;
                    }
                } else {
                    if (map.containsKey(c)) {
                        current = map.get(c);
                        current.cnt++;
                    } else {
                        Node node = new Node(c, 1);
                        map.put(c, node);
                        current = node;
                    }
                }
            }
        }

        for (int i = 0; i < len; i++) {
            String str = words[i];
            int strLen = str.length();

            int depth = 1;

            int j = 0;
            Node n = map.get(str.charAt(0));

            while (n.cnt != 1 && depth != strLen) {
                n = n.map.get(str.charAt(++j));
                depth++;
            }

            answer += depth;
        }

        return answer;
    }
  }