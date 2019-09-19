import java.util.*;

/****************************
 * 기지국의 위치가 최적이 되려면
 * 기지국과 최대한 겹치지 않고 모든 범위를 
 * 사용하는 것이 가장 최적의 선택이 된다. 
 * 
 * ex)
 *   
 * l     <  p  >             <  p
 * ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
 * 1  2  3  4  5  6  7 8  9 10 11
 * 
 * 가장 왼쪽부터 스타트한다. 현재 위치가
 * 가장 가까운 p의 영역에 있는지 아닌지를
 * 판단한다.영역에 있지 않기 때문에
 * 설치하는 최적의 경우는 모든 영역을
 * 쓸수 있는 곳이므로 l은 2에 설치하고
 * 4로 넘어가는 것이 최적이다. 4에
 * 도착했으므로 p의 영역에 있게 되므로
 * 설치할 필요가 없다.그러므로 6으로
 * 이동시켜주면된다. 이를 반복하여
 * 최적의 갯수를 구할 수 있다.
 ****************************/

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(11, new int[] { 4, 11 }, 1));
        System.out.println(solution(16, new int[] { 9 }, 2));
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int idx = 0;
        int location = 1;

        while (location <= n) {
            if (idx < stations.length && location >= stations[idx] - w) {
                location = stations[idx] + w + 1;
                idx++;
            } else {
                location += 2 * w + 1;
                answer++;
            }
        }

        return answer;
    }
}