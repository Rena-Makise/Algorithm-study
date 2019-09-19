import java.util.*;

class Solution {
    // static을 붙이면 정확성 통과가 안됨;;;;;;
    int possible = 0;
    int maxThree = 0;

    public int solution(int n) {
        maxThree = (int)(Math.log10(n) / Math.log10(3));
        threeStep(n - 2, 0, 2);
        return possible;
    }

    public void threeStep(int num, int cntStar, int cntPlus) {
        if (num < 1) return;
        // & : 두 조건 모두 검사 ; && : 앞의 것이 참일때만 검사
        if (num == 1 & cntPlus == 2 * cntStar) {
            possible++;
            return;
        }

        if (2 * maxThree < cntPlus) return;
        if (num % 3 == 0) {
            if (2 * (cntStar + 1) <= cntPlus) 
                threeStep(num / 3, cntStar + 1, cntPlus);
            threeStep(num - 3, cntStar, cntPlus + 3);
        } else if (num % 3 == 2) {
            threeStep(num - 2, cntStar, cntPlus + 2);
        } else if (num % 3 == 1) {
            threeStep(num - 1, cntStar, cntPlus + 1);
        }
        return;
    }
  }