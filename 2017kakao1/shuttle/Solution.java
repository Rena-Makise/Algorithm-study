import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(solution(10, 60, 45, new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59"
                , "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }

    /**
     * hh:mm의 String을 입력받아 addMin만큼 더한 시간 리턴
     * @param hhmm
     * @param addMin
     * @return
     */
    public static String getAddTime(String hhmm, int addMin) {
        long longTime = getMinLong(hhmm);
        longTime = longTime + addMin;
        int hh = (int) longTime / 60;
        int mm = (int) longTime % 60;
        return String.format("%02d", hh) + ":" + String.format("%02d", mm);
        
    }

    /**
     * long형태의 분으로 리턴
     * @param hhmm
     * @return
     */
    public static long getMinLong(String hhmm) {
        String arr[] = hhmm.split(":");
        int hh = Integer.parseInt(arr[0]);
        int mm = Integer.parseInt(arr[1]);
        return hh * 60 + mm;
    }

    /**
     * String 형태의 표현으로 리턴
     * @param min
     * @return
     */
    public static String getMinString(long min) {
        int hh = (int) min / 60;
        int mm = (int) min % 60;
        return String.format("%02d", hh) + ":" + String.format("%02d", mm);
    }
    
    /**
     * 마지막으로 탈 수 있는 버스 시간 계산
     * @param n  셔틀 운행 횟수
     * @param t  셔틀 운행 간격
     * @param m  한 셔틀에 탈 수 있는 최대 크루 수
     * @param timetable  크루가 대기열에 도착하는 시간을 모은 배열
     * @return
     */
    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        long timetableM[] = new long[timetable.length];

        // timetable을 분으로 수정
        for (int i = 0; i < timetable.length; i++)
            timetableM[i] = getMinLong(timetable[i]);

        // 먼저 온 사람 순으로 정렬
        Arrays.sort(timetableM);

        int curPos = 0; // 여태까지 버스에 탑승한 인원
        int curAdded = 0; // 현재 버스에 탑승한 인원
        long busTime = 9 * 60; // 오는 버스 시간
        for (int i = 0; i < n; i++) {
            curAdded = 0;
            for (int j = curPos; j < timetableM.length; j++) {
                // 현재 버스 시간 이전에 도착한 사람이 있는 경우
                if (timetableM[j] <= busTime) {
                    curPos++;
                    curAdded++;

                    // 만약 현재 버스가 꽉 차면 break
                    if (curAdded == m) {
                        break;
                    }
                }
            }
            busTime += t;
        }

        long result = 0;
        if (curAdded < m) {
            result = busTime - t;
        } else {
            result = timetableM[curPos - 1] - 1;
        }

        answer = getMinString(result);
        return answer;
    }
  }