import java.util.*;

class Document {
    int location;
    int priority;
    public Document (int location, int priorty) {
        this.location = location;
        this.priority = priorty;
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{ 2, 1, 3, 2 }, 2));
        System.out.println(solution(new int[]{ 1, 1, 9, 1, 1, 1 }, 0));
    }

    public static int solution(int[] priorities, int location) {
        List<Document> list = new LinkedList<>();
        int answer = 1;
        for (int i = 0; i < priorities.length; i++) {
            list.add(new Document(i, priorities[i]));
        }

        while (list.size() > 1) {
            for (int i = 1; i < list.size(); ++i) {
                if (list.get(i).priority > list.get(0).priority) {
                    list.add(list.get(0));
                    list.remove(0);
                    break;
                }
                if(i == list.size() - 1) {
                    if (list.get(0).location == location) return answer;
                    list.remove(0);
                    answer++;
                }
            }
        }
        return answer;
    }
}