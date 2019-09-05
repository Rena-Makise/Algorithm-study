import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("ABCDEFG", new String[] {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(solution("CC#BCC#BCC#BCC#B", new String[] {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(solution("ABC", new String[] {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }

    static int getMin(String startTime, String endTime) {
        String[] arr;
        arr = startTime.split(":");
        int startMin = Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);

        arr = endTime.split(":");
        int endMin = Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);

        return Math.abs(endMin - startMin);
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int lastTime = -1;
        // 문자의 혼동을 피하기 위해 소문자로 변경하여 스트링 비교
        m = m.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("G#", "g").replace("A#", "a");

        for (String musicinfo : musicinfos) {
            String[] arr = musicinfo.split(",");

            int playLen = getMin(arr[0], arr[1]); // 실제 재생한 분
            String orgPlay = arr[3]; // 원곡의 음표
            orgPlay = orgPlay.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("G#", "g").replace("A#", "a");

            String realPlay = "";  // 실제 재생된 음표를 구함 rearPlay
            int j = 0;
            for(int i = 0; i < playLen ; i++) {
                j = i % orgPlay.length();
                realPlay += orgPlay.charAt(j);
            }
            
             // 조건에 맞는 음악이 여러개인 경우 재생시간이 긴곡, 재생시간도 같다면 먼저 입력된 곡
            if(realPlay.contains(m)) {
                if (lastTime != -1) {
                    if (playLen > lastTime) {
                        answer = arr[2];
                        lastTime = playLen;
                    }
                } else {
                    answer = arr[2];
                    lastTime = playLen;
                }
            }
        }

        return answer;
    }
  }