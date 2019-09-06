import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution 
{
    public static void main(String[] args) 
    {
        String[] arr = solution(new String[] { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
                "Enter uid1234 Prodo", "Change uid4567 Ryan" });
        for (int i = 0; i < arr.length; ++i)
            System.out.println(arr[i]);
    }

    public static String[] solution(String[] record) 
    {
        Map<String, String> idMap = new HashMap<>(); // 유저 목록
        List<String[]> tmp = new LinkedList<>(); // Change를 제외한 로그

        for (String records : record) 
        {
            String[] keyWord = records.split(" ");
            if (keyWord[0].equals("Enter")) 
            {
                idMap.put(keyWord[1], keyWord[2]);
                tmp.add(keyWord);
            } else if (keyWord[0].equals("Change")) 
            {
                idMap.put(keyWord[1], keyWord[2]); // Change시에는 유저에만 반영
            } else 
            {
                tmp.add(keyWord);
            }
        }

        String[] answer = new String[tmp.size()];
        int idx = 0;
        for (String[] keyWords : tmp) 
        {
            String nickName = idMap.get(keyWords[1]);
            if (keyWords[0].equals(("Enter")))
                answer[idx++] = nickName + "님이 들어왔습니다.";
            else
                answer[idx++] = nickName + "님이 나갔습니다.";
        }
        return answer;
    }
}