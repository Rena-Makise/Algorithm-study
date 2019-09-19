import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static final int MAP_SIZE = 11;
    static int[][] direction = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
    static boolean[][][][] map = new boolean[MAP_SIZE][MAP_SIZE][MAP_SIZE][MAP_SIZE];
    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU"));
        System.out.println(solution("LULLLLLLU"));
        System.out.println(solution("ULUUUDDDL"));
    }

    public static boolean safeMap(int x, int y) {
        return (x >= 0 && x < MAP_SIZE) && (y >= 0 && y < MAP_SIZE);
    }

    public static int checkRoute(Point user, int d) {
        int result = 0;
        int dx = user.x + direction[d][0];
        int dy = user.y + direction[d][1];

        if (safeMap(dx, dy)) {
            if (!map[user.x][user.y][dx][dy]) {
                map[user.x][user.y][dx][dy] = true;
                map[dx][dy][user.x][user.y] = true;
                result = 1;
            }
            user.move(dx, dy);
        }

        return result;
    }

    public static int solution(String dirs) {
        int answer = 0;
        Point user = new Point(5, 5);

        for (int i = 0; i < dirs.length(); ++i) {
            switch (dirs.charAt(i)) {
                case 'D' : {
                    answer += checkRoute(user, 0);
                    break;
                }
                case 'L' : {
                    answer += checkRoute(user, 1);
                    break;
                }
                case 'R' : {
                    answer += checkRoute(user, 2);
                    break;
                }
                case 'U' : {
                    answer += checkRoute(user, 3);
                    break;
                }
            }
        }

        // Set<String> visited = new HashSet<>();
        // int startX = 5, startY = 5;

        // for (int i = 0; i < dirs.length(); ++i) {
        //     switch(dirs.charAt(i)) {
        //         case 'U' :
        //             if (startY - 1 >= 0) {
        //                 String s1 = Integer.toString(startX) + Integer.toString(startY) + Integer.toString(startX) + Integer.toString(startY - 1);
        //                 String s2 = Integer.toString(startX) + Integer.toString(startY - 1) + Integer.toString(startX) + Integer.toString(startY);
        //                 startY -= 1;
        //                 if (!visited.contains(s1) && !visited.contains(s2)) {
        //                     visited.add(s1);
        //                     visited.add(s2);
        //                     answer++;
        //                 }
        //             }
        //             break;
        //         case 'D' : 
        //             if (startY + 1 <= 10) {
        //                 String s1 = Integer.toString(startX) + Integer.toString(startY) + Integer.toString(startX) + Integer.toString(startY + 1);
        //                 String s2 = Integer.toString(startX) + Integer.toString(startY + 1) + Integer.toString(startX) + Integer.toString(startY);
        //                 startY += 1;
        //                 if (!visited.contains(s1) && !visited.contains(s2)) {
        //                     visited.add(s1);
        //                     visited.add(s2);
        //                     answer++;
        //                 }
        //             }
        //             break;
        //         case 'L' : 
        //             if (startX - 1 >= 0) {
        //                 String s1 = Integer.toString(startX) + Integer.toString(startY) + Integer.toString(startX - 1) + Integer.toString(startY);
        //                 String s2 = Integer.toString(startX - 1) + Integer.toString(startY) + Integer.toString(startX) + Integer.toString(startY);
        //                 startX -= 1;
        //                 if (!visited.contains(s1) && !visited.contains(s2)) {
        //                     visited.add(s1);
        //                     visited.add(s2);
        //                     answer++;
        //                 }
        //             }
        //             break;
        //         case 'R' :
        //             if (startX + 1 <= 10) {
        //                 String s1 = Integer.toString(startX) + Integer.toString(startY) + Integer.toString(startX + 1) + Integer.toString(startY - 1);
        //                 String s2 = Integer.toString(startX + 1) + Integer.toString(startY - 1) + Integer.toString(startX) + Integer.toString(startY);
        //                 startX += 1;
        //                 if (!visited.contains(s1) && !visited.contains(s2)) {
        //                     visited.add(s1);
        //                     visited.add(s2);
        //                     answer++;
        //                 }
        //             }
        //     }
        // }



        return answer;
    }
}