import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }

    /**
     * 중복 허용 다중집합의 합집합
     * @param list1
     * @param list2
     * @return
     */
    public static ArrayList<String> getUnionList(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> cloneList1 = (ArrayList<String>) list1.clone();
        ArrayList<String> cloneList2 = (ArrayList<String>) list2.clone();
        ArrayList<String> unionList = new ArrayList<String>();
        for (String str : cloneList1) {
            if (cloneList2.contains(str)) {
                cloneList2.remove(str);
            }
            unionList.add(str);
        }
        unionList.addAll(cloneList2);
        return unionList;
    }

    /**
     * 중복 허용 다중집합의 교집합
     * @param list1
     * @param list2
     * @return
     */
    public static ArrayList<String> getIntersectionList(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> cloneList1 = (ArrayList<String>) list1.clone();
        ArrayList<String> cloneList2 = (ArrayList<String>) list2.clone();
        ArrayList<String> interList = new ArrayList<String>();
        for (String str : cloneList1) {
            if (cloneList2.contains(str)) {
                interList.add(str);
                cloneList2.remove(str);
            }
        }

        return interList;
    }

    public static int solution(String str1, String str2) {
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();

        String tempStr = "";

        // 문자열을 두 개씩 잘라서 영문자로만 이루어져있는지 체킹 후 리스트에 반영
        // 주로 쓰이는 정규식 패턴 
        // 영문자만 : ^[a-zA-Z]*$ (* : 앞 문자가 없을수도 있 / + : 적어도 하나 이상)
        // 숫자만 : ^[0-9]*$
        // 한글만 : ^[가-힣]*$
        // 영어 & 숫자만 : ^[a-zA-Z0-9]*$
        // E-Mail : ^[a-zA-Z0-9]+@[a-zA-Z0-9]+$
        // 휴대폰 : ^01(?:0|1|[6-9]) - (?:\d{3}|\d{4}) - \d{4}$
        // 일반전화 : ^\d{2,3} - \d{3,4} - \d{4}$
        // 주민등록번호 : \d{6} \- [1-4]\d{6}
        // IP 주소 : ([0-9]{1,3}) \. ([0-9]{1,3}) \. ([0-9]{1,3}) \. ([0-9]{1,3})
        for (int i = 0; i < str1.length() - 1; i++) {
            tempStr = str1.substring(i, i + 2).toUpperCase();
            if (tempStr.matches("^[A-Z]+$")) {
                list1.add(tempStr);
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            tempStr = str2.substring(i, i + 2).toUpperCase();
            if (tempStr.matches("^[A-Z]+$")) {
                list2.add(tempStr);
            }
        }

        ArrayList<String> unionList = getUnionList(list1, list2);
        ArrayList<String> intersectionList = getIntersectionList(list1, list2);

        if (list1.size() == 0 && list2.size() == 0)
            return 65536;
        else 
            return (int) ((double) intersectionList.size() / (double) unionList.size() * (double) 65536);
    }
  }