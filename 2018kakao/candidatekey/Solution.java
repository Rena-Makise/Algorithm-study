import java.util.*;

class Solution {
    public static void main(String[] args) {
        int result = solution(new String[][] {{"100","ryan","music","2"}, {"200","apeach","math","2"}, 
            {"300","tube","computer","3"}, {"400","con","computer","4"}, {"500","muzi","music","3"},
            {"600","apeach","music","2"}});

        System.out.println("후보키 : " + result);
    }

    /**
     * 유일성 체크
     * @param realtion  데이터
     * @param rowsize  데이터 갯수
     * @param colsize  데이터 속성 갯수
     * @param subset  부분집합 원소 갯수
     * @return
     */
    public static boolean check(String[][] realtion, int rowsize, int colsize, int subset) {
        for (int a = 0; a < rowsize - 1; ++a)
        {
            for (int b = a + 1; b < rowsize; ++b) 
            {
                boolean isSame = true;
                for (int k = 0; k < colsize; ++k)
                {
                    if ((subset & 1 << k) == 0) continue;
                    if (realtion[a][k].equals(realtion[b][k]) == false)
                    {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) return false;
            }
        }
        return true;
    }

    public static int solution(String[][] relation) {
        int answer = 0;
        int rowsize = relation.length; // 데이터 갯수
        int colsize = relation[0].length; // 데이터 속성 갯수
        List<Integer> candidates = new LinkedList<Integer>();

        // 먼저 유일성을 체크
        // 속성이 각각 반영될 수도 있고 반영되지 않을 수도 있다.  (1 or 0)
        // 1 << colsize  =  2^colsize
        for (int i = 1; i < 1 << colsize; ++i)
        {
            if (check(relation, rowsize, colsize, i) == true)
                candidates.add(i);
        }

        // 체크된 후 남은 속성들을 적은 속성 순으로 정렬
        Collections.sort(candidates, new Comparator<Integer>(){
            int countBit(int n) {
                int ret = 0;
                while (n != 0)
                {
                    if ((n & 1) != 0) ++ret;
                    n = n >> 1;
                }
                return ret;
            }
            @Override
            public int compare(Integer a, Integer b) {
                int x = countBit(a), y = countBit(b);
                return x - y;
            }
        });

        // 최소성 체크 : 적은 속성 순으로 비교해서 중복되는 속성을 가진 것을 제외
        while (candidates.size() != 0)
        {
            int n = candidates.remove(0);
            ++ answer;

            for (Iterator<Integer> it = candidates.iterator(); it.hasNext(); )
            {
                int c = it.next();
                if((n & c) == n)
                    it.remove();
            }
        }

        return answer;
    }
}