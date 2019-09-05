import java.util.*;

class Person {
    int idx;
    int score;

    public Person(int idx, int score) {
        this.idx = idx;
        this.score = score;
    }
}

class Solution {
    public static void main(String[] args) {
        int[] result1 = solution(new int[] { 1,2,3,4,5 });
        int[] result2 = solution(new int[] { 1,3,2,4,2 });

        for(int i : result1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int j : result2) {
            System.out.print(j + " ");
        }
    }

    public static int[] solution(int[] answers) {
        int[] p1 = { 1, 2, 3, 4, 5 };
        int[] p2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] p3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        List<Person> list = new LinkedList<>();
        List<Integer> flist = new LinkedList<>();
        int maxScore = 0;

        int score1 = 0;
        int score2 = 0;
        int score3 = 0;

        for (int i = 0; i < answers.length; i++) {
            if (p1[i % 5] == answers[i]) score1++;
            if (p2[i % 8] == answers[i]) score2++;
            if (p3[i % 10] == answers[i]) score3++;
        }

        list.add(new Person(1, score1));
        list.add(new Person(2, score2));
        list.add(new Person(3, score3));

        for (Person p : list) {
            if (p.score > maxScore)
                maxScore = p.score;
        }

        for (Person p : list) {
            if(p.score == maxScore)
                flist.add(p.idx);
        }

        
        int[] answer = new int[flist.size()];
        for (int i = 0; i < flist.size(); i++) {
            answer[i] = flist.get(i);
        }

        return answer;
    }
}