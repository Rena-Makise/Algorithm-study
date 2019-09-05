import java.util.*;

class Disk implements Comparable<Disk> {
    int start;
    int time;
    public Disk (int start, int time) {
        this.start = start;
        this.time = time;
    }

    @Override
    public int compareTo(Disk d) {
        if (this.time < d.time)
            return -1;
        else if (this.time == d.time)
            return this.start - d.start;
        else
            return 1;
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } }));    
    }

    public static int solution(int[][] jobs) {
        Queue<Disk> que = new PriorityQueue<>();
        List<Disk> list = new LinkedList<>();
        int time = 0;
        int answer = 0;

        for (int i = 0; i < jobs.length; ++i) {
            que.offer(new Disk(jobs[i][0], jobs[i][1]));
        }

        for(int i = 0; i < jobs.length; ++i) {
            list.add(que.poll());
        }

        while (list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                if (time >= list.get(i).start) {
                    time += list.get(i).time;
                    answer += time - list.get(i).start;
                    list.remove(i);
                    break;
                }

                // 시작시간이 현재 시간보다 이전인 것이 없다면 시간을 증가시켜줘야한다!!!
                if (i == list.size() - 1) time++;
            }
        }

        answer = answer / jobs.length;

        return answer;
    }
}