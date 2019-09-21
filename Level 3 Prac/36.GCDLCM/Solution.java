class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int lcm = arr[0];
        
        for (int i = 1; i < arr.length; ++i) {
            lcm = getLcm(lcm, arr[i]);
        }
        
        answer = lcm;
        return answer;
    }
      
    public int getGcd(int a, int b) {
        int tmp;
        while (b != 0) {
            tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
      
      public int getLcm(int a, int b) {
          if (a <= 0 || b <= 0) return -1;
          return a * b / getGcd(a, b);
      }
  }