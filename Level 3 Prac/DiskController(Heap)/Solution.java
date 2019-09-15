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
        if (this.time < d.time)  // 짧게 걸리는 작업 순으로
            return -1;
        else if (this.time == d.time)  // 만약 걸리는 시간이 같으면 먼저 들어온 순으로
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
        Queue<Disk> que = new PriorityQueue<>();  // 정렬을 위해 우선순위큐 사용
        List<Disk> list = new LinkedList<>();
        int time = 0;
        int answer = 0;

        for (int i = 0; i < jobs.length; ++i) {
            que.offer(new Disk(jobs[i][0], jobs[i][1]));
        }

        for(int i = 0; i < jobs.length; ++i) {
            list.add(que.poll());  // 정렬된 작업을 리스트에 집어넣는다.
        }

        while (list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                if (time >= list.get(i).start) {  // 해당 시간에 시작할 수 있는 작업을 찾는다.
                    time += list.get(i).time;
                    answer += time - list.get(i).start;
                    list.remove(i);
                    break;
                }

                if (i == list.size() - 1) time++;  // 만약 해당 시간에 시작할 수 있는 작업이 없으면 시간을 늘여주어야 한다.
            }
        }

        answer = answer / jobs.length;

        return answer;
    }
}