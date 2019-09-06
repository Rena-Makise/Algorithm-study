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
            return this.stageNum - n.stageNum; // 실패율이 같다면 작은 번호의 스테이지가 먼저
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
        int[] clear = new int[N]; // 각 스테이지별 클리어한 유저 수
        int[] fail = new int[N]; // 각 스테이지별 실패한 유저 수
        Arrays.sort(stages);


        for(int stage : stages)
        {
            if(N >= stage)
            {
                fail[stage - 1]++; // 현재 스테이지는 실패
                // 이전 스테이지까지는 전부 성공
                for(int i = 0; i < stage; ++i)
                    clear[i]++;
            }
            else
            {
                // stage가 N보다 클 경우에는 모든 스테이지를 클리어했다는 뜻이므로.
                for(int i = 0; i < N; ++i)
                    clear[i]++;
            }
        }

        // 각 스테이지별 정보를 담을 리스트
        List<Stage> list = new ArrayList<>();
        for(int i = 0; i < N; ++i)
        {
            double failure;
            int player = clear[i] + fail[i]; // 각 층별 플레이어
            if(player != 0) // 해당 층에 사람이 있으면
                failure = (double)fail[i]/player; // 실패율 생성
            else
                failure = 0; // 사람 없으면 실패율 0 (문제 조건)

            list.add(new Stage(i + 1, failure));
        }

        Collections.sort(list);  // 실패율이 높은 스테이지부터 내림차순으로 정렬
        for(int i = 0; i < N; ++i)
            answer[i] = list.get(i).stageNum;

        return answer;
    }
}