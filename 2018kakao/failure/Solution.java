import java.util.*;

class Stage implements Comparable<Stage> {
    int stageNum;
    double failure; 

    public Stage(int stageNum, double failure)
    {
        this.stageNum = stageNum;
        this.failure = failure;
    }

    @Override
    public int compareTo(Stage n) 
    {
        if(n.failure > this.failure)
            return 1;
        else if(n.failure == this.failure)
            return this.stageNum - n.stageNum;
        else
            return -1;
    }
}

class Solution {
    public static void main(String[] args) {
        int[] ex1 = solution(5, new int[] { 2, 1, 2, 6, 2, 4, 3, 3 });
        int[] ex2 = solution(4, new int[] { 4, 4, 4, 4, 4 });
        
        for(int stage : ex1)
            System.out.print(stage + " ");

        System.out.println();

        for(int stage : ex2)
            System.out.print(stage + " ");
    }

    /**
     * 실패율 (스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어의 수)
     * @param N  전체 스테이지의 개수
     * @param stages  게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열
     * @return  실패율이 높은 스테이지부터 내림차순
     */
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] clear = new int[N];
        int[] fail = new int[N];
        Arrays.sort(stages);


        for(int stage : stages)
        {
            if(N >= stage)
            {
                fail[stage - 1]++;
                for(int i = 0; i < stage; ++i)
                    clear[i]++;
            }
            else
            {
                for(int i = 0; i < N; ++i)
                    clear[i]++;
            }
        }

        List<Stage> list = new ArrayList<>();
        for(int i = 0; i < N; ++i)
        {
            double failure;
            int player = clear[i] + fail[i];
            if(player != 0)
                failure = (double)fail[i]/player;
            else
                failure = 0;

            list.add(new Stage(i + 1, failure));
        }

        Collections.sort(list);
        for(int i = 0; i < N; ++i)
            answer[i] = list.get(i).stageNum;

        return answer;
    }
}