import java.util.*;

class Truck {
    int weight;
    int time;
    public Truck (int weight, int time) {
        this.weight = weight;
        this.time = time;
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[]{ 7,4,5,6 }));

    }
    
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> waitQueue = new LinkedList<>();
        Queue<Truck> onQueue = new LinkedList<>();
        int answer = 0;
        int total_weight = 0;

        for (int i : truck_weights) {
            waitQueue.offer(new Truck(i, bridge_length));
        }

        while (!(onQueue.isEmpty() && waitQueue.isEmpty())) {
            answer++;

            if (!onQueue.isEmpty() && onQueue.peek().time <= 0) {
                total_weight -= onQueue.poll().weight;
            }
                
            if (!waitQueue.isEmpty() && total_weight + waitQueue.peek().weight <= weight) {
                total_weight += waitQueue.peek().weight;
                onQueue.offer(waitQueue.poll());
            }

            for (Truck t : onQueue) {
                t.time -= 1;
            }
        }

        return answer;
    }
}