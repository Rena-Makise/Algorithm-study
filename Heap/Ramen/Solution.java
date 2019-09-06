import java.util.*;

class Supply implements Comparable<Supply> {
    int date;
    int supply;
    
    public Supply (int date, int supply) {
        this.date = date;
        this.supply = supply;
    }

    @Override
    public int compareTo(Supply s) {
        if (this.supply > s.supply)
            return -1;
        else if (s.supply == this.supply) 
            return this.date - s.date;
        else
            return 1;
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[] { 4,10,15 }, new int[] { 20,5,10 }, 30));
    }

    public static int solution(int stock, int[] dates, int[] supplies, int k) {
        Queue<Supply> que = new PriorityQueue<>();
        List<Supply> list = new ArrayList<>();
        int answer = 0;
        for (int i = 0; i < supplies.length; ++i) {
            que.add(new Supply(dates[i], supplies[i]));
        }
        // que는 for 간단 순회가 안되나보다.... poll로 빼내야...
        // que의 사이즈를 구하는건 조낸 오래걸리나보다....
        for (int i = 0; i < supplies.length; ++i) {
            list.add(que.poll());
        }
        while (stock < k) {
            for (int i = 0; i < list.size(); ++i) {
                if (stock >= list.get(i).date) { // 만약 남은 양이 해당 일자보다 적으면 그날까지 기다릴 수가 없다....
                    stock += list.get(i).supply;
                    list.remove(i);
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}