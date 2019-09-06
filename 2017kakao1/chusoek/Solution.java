import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));
        System.out.println(solution(new String[]{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"}));
        System.out.println(solution(new String[]{"2016-09-15 20:59:57.421 0.351s"
                , "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s"
                , "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s"
                , "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s"
                , "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s"
                , "2016-09-15 21:00:02.066 2.62s"}));
    }

    // 로그 구조 클래스 - 들어오는 시간을 시작시간 종료시간으로 파싱
    public static class LogVo {
        long startDtm, endedDtm;
        String orgStr;

        public LogVo(long startDtm, long endedDtm, String orgStr) {
            this.startDtm = startDtm;
            this.endedDtm = endedDtm;
            this.orgStr = orgStr;
        }

        public long getStartDtm() {
            return startDtm;
        }

        public long getEndedDtm() {
            return endedDtm;
        }

    }

    public static int solution(String[] lines) {
        // LogVo를 담을 List
        ArrayList<LogVo> list = new ArrayList<LogVo>();

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        for (String s : lines) {
            String arr[] = s.split(" ");
            try {
                // 시간 계산을 간단히 처리하기 위해서 모두 시작시각, 종료시각을 long으로 처리
                // getTime은 Date를 밀리세컨드로 변환해서 long형 숫자 데이터로 반환
                long endedDtm = dt.parse(arr[0] + " " + arr[1]).getTime();
                //System.out.println("endedDtm = "+endedDtm);
                // 실행 시간 처리 (1s = 1000ms)
                long proceDtm = (long)(Double.parseDouble(arr[2].replace("s", "")) * 1000);
                //System.out.println("proceDtm = "+proceDtm);
                long startDtm = endedDtm - proceDtm + 1;
                //System.out.println("startDtm = "+startDtm);
                // LogVo를 생성하여 list에 추가
                list.add(new LogVo(startDtm, endedDtm, s));
            } catch (ParseException e) {
                e.printStackTrace();
                return -1;
            }
        }

        int answer = 0; // 최대값
        for (LogVo sourceVo : list) {
            long startDtm = sourceVo.getStartDtm(); // 시작시각
            long endedDtm = sourceVo.getEndedDtm(); // 종료시각
            int startCnt = 0; // 현재 로그 시작구간에서 실행중인 트랜젝션 갯수
            int endedCnt = 0; // 현재 로그 종료구간에서 실행중인 트랜젝션 갯수
            // 2중 for 문. n^2번 검사
            // 1초당 처리되는 최대값을 구하는 알고리즘.
            // 각 작업이 시작되는 시점, 그리고 끝나는 시점에서 값이 변하게 된다.
            // 따라서 시작지점과 끝나는 시점에서 앞뒤로 1초로 탐색을 하여 실행되고 있는 작업을 연산
            for (LogVo targetVo : list) {
                if (startDtm <= targetVo.getEndedDtm() && startDtm + 999 >= targetVo.getStartDtm())
                    startCnt++;
                if (endedDtm <= targetVo.getEndedDtm() && endedDtm + 999 >= targetVo.getStartDtm())
                    endedCnt++;
            }

            answer = startCnt > answer ? startCnt : answer;
            answer = endedCnt > answer ? endedCnt : answer;
        }

        return answer;
    }
  }