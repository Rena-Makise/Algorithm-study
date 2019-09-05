import java.util.*;

class Food {
    int time; // 먹는 데 걸리는 시간
    int idx; // 해당 음식의 idx

    Food (int time, int idx) {
        this.time = time;
        this.idx = idx;
    }
}

class Solution {

    public static void main(String[] args) {
        System.out.println("음식 : " + solution(new int[] { 3, 1, 2 }, 5));
    }

    /**
     * 먹방 라이브
     * @param food_times  음식을 모두 먹는데 필요한 시간이 담겨있는 배열
     * @param k  네트워크 장애 발생 초
     * @return  장애 이후 먹어야 하는 음식 번호
     */
    public static int solution(int[] food_times, long k) {
        List<Food> foods = new LinkedList<Food>();
        int remainFood = food_times.length;

        for (int i = 0; i < remainFood; ++i)
            foods.add(new Food(food_times[i], i + 1));

        // 음식을 먹는데 걸리는 시간 별 정렬
        Comparator<Food> CompTime = new Comparator<Food>(){
            @Override
            public int compare(Food a, Food b) {
                return a.time - b.time;
            }
        };

        // 음식의 인덱스 별 정렬
        Comparator<Food> CompIdx = new Comparator<Food>(){
            @Override
            public int compare(Food a, Food b) {
                return a.idx - b.idx;
            }
        };

        // 먼저 시간 순 정렬
        foods.sort(CompTime);


        long preTime = 0; // 이전 음식을 먹는데 걸린 시간
        int i = 0; // 루프문 idx
        for (Food food : foods)
        {
            long diff = food.time - preTime;
            if (diff != 0)
            {
                long spend = diff * remainFood;
                if (spend <= k)
                {
                    k -= spend;
                    preTime = food.time;
                }
                else
                {
                    k %= remainFood;
                    foods.subList(i, food_times.length).sort(CompIdx);
                    return foods.get(i + (int)k).idx;
                }
            }
            ++i;
            --remainFood;
        }
        return -1;
    }
}