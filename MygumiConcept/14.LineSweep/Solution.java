import java.util.*;

class Point implements Comparable<Point> {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        return x < p.y && y > p.y ? 1 : -1;
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[][] { { 0, 0 }, { 10, 10 }, { 0, 10 }, { 10, 0 } }));
    }

    static class ComparatorDescending implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.x < p2.x) {
                return -1;
            } else if (p1.x == p2.x) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static class ComparatorSet implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.y == p2.y) {
                if (p1.x < p2.x) {
                    return -1;
                } else if (p1.x == p2.x) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return p1.y < p2.y ? 1 : -1;
            }
        }
    }

    public static int distanceDouble(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }


    /**
     * 가장 가까운 두 점
     * Line Sweep알고리즘 이용
     * @param n  점의 갯수
     * @param arr  점 좌표 배열
     * @return  n개의 점들 중 가장 가까운 두 점의 거리의 제곱
     */
    public static int solution(int n, int[][] arr) {
        List<Point> list = new ArrayList<>();
        TreeSet<Point> candidate = new TreeSet<>(new ComparatorSet());
        int answer = 0;

        for (int i = 0; i < n; ++i) {
            list.add(new Point(arr[i][0], arr[i][1]));
        }

        Collections.sort(list, new ComparatorDescending());

        candidate.add(list.get(0));
        candidate.add(list.get(1));
        answer = distanceDouble(list.get(0), list.get(1));
    
        int start = 0;
        for (int i = 2; i < n; i++) {
          Point now = list.get(i);
    
          // x axis
          while (start < i) {
            Point pivot = list.get(start);
            int x = pivot.x - now.x;
    
            if (x * x > answer) {
              candidate.remove(pivot);
              start += 1;
            } else {
              break;
            }
          }
    
          int d = (int) Math.sqrt((double) answer) + 1;
          Point lower_point = new Point(now.y - d, now.y + d);
          SortedSet<Point> lower = candidate.tailSet(lower_point);
          Iterator<Point> it_lower = lower.iterator();
    
          while (it_lower.hasNext()) {
            Point p = it_lower.next();
            d = distanceDouble(now, p);
            if (d < answer) {
              answer = d;
            }
          }
          candidate.add(list.get(i));
        }

        return answer;
    }
}