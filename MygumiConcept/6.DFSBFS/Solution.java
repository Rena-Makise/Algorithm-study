import java.util.*;

// 양방향 그래프. 가중치가 없는 경우
class Solution {
    public static void main(String[] args) {
        solution(4, 5, 1, new int[][] { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 }, { 3, 4 } });
    }

    /**
     * 그래프가 주어질 때 DFS와 BFS의 결과를 출력하라
     * @param n  정점의 갯수
     * @param m  간선의 갯수
     * @param v  탐색을 시작할 정점 번호
     * @param a  그래프 인접행렬
     */
    public static void solution(int n, int m, int v, int[][] a) {
        boolean[] c = new boolean[n + 1];

        // 인접행렬 arr
        int[][] arr = new int[n + 1][n + 1];

        for (int i = 0; i < m; ++i) {
            arr[a[i][0]][a[i][1]] = 1;
            arr[a[i][1]][a[i][0]] = 1;
        }
        
        System.out.println("인접행렬 DFS, BFS");
        ArrayList<Integer> list = new ArrayList<>();
        dfs(arr, c, v, list);
        int[] temp = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            temp[i] = list.get(i);
        } 
        for (int i : temp) {
            System.out.print(i + " ");
        }
        System.out.println();
        Arrays.fill(c, false);
        temp = bfs(arr, c, v);
        for (int i : temp) {
            System.out.print(i + " ");
        }
        Arrays.fill(c, false);
        System.out.println();

        // 인접 리스트 alist
        ArrayList<Integer>[] alist = (ArrayList<Integer>[]) new ArrayList[n + 1];

        for (int i = 0; i <= n; ++i) {
            alist[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; ++i) {
            alist[a[i][0]].add(a[i][1]);
            alist[a[i][1]].add(a[i][0]);
        }

        for (int i = 1; i <= n; ++i) {
            Collections.sort(alist[i]);
        }

        System.out.println("인접리스트 DFS, BFS");
        list = new ArrayList<>();
        dfs(alist, c, v, list);
        temp = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            temp[i] = list.get(i);
        } 
        for (int i : temp) {
            System.out.print(i + " ");
        }
        Arrays.fill(c, false);
        System.out.println();
        temp = bfs(alist, c, v);
        for (int i : temp) {
            System.out.print(i + " ");
        }
    }

    /**
     * 재귀 DFS - 인접행렬 이용
     * @param a  인접행렬
     * @param c  방문여부
     * @param v  탐색을 시작할 정점 번호
     * @param list  DFS 결과를 담을 리스트
     */
    public static void dfs(int[][] a, boolean[] c, int v, ArrayList<Integer> list) {
        int n = a.length - 1;
        c[v] = true;
        list.add(v);

        for (int i = 1; i <= n; ++i) {
            if (a[v][i] == 1 && !c[i]) {
                dfs(a, c, i, list);
            }
        }
    }

    /**
     * 스택 DFS - 인접행렬 이용
     * DFS는 사실 스택을 사용한다!
     * @param a  인접행렬
     * @param c  방문여부
     * @param v  탐색을 시작할 정점 번호
     * @param flag  방문여부flag
     * @return
     */
	public static int[] dfs(int[][] a, boolean[] c, int v, boolean flag) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        int n = a.length - 1;
        
		stack.push(v);
		c[v] = true;
		list.add(v);

		while (!stack.isEmpty()) {
			int vv = stack.peek();

			flag = false;

			for (int i = 1; i <= n; i++) {
                if (a[vv][i] == 1 && !c[i]) {
					stack.push(i);
					list.add(i);

					c[i] = true;
					flag = true;
					break;
				}

			}

			if (!flag) {
				stack.pop();
			}

		}
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        } 

        return answer;
    }
    
    /**
     * 큐 BFS - 인접행렬 이용
     * @param a  인접행렬
     * @param c  방문여부
     * @param v  탐색을 시작할 정점 번호
     * @return
     */
    public static int[] bfs(int[][] a, boolean[] c, int v) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        int n = a.length - 1;

        q.add(v);
        c[v] = true;

        while (!q.isEmpty()) {
            v = q.poll();
            list.add(v);

            for (int i = 1; i <= n; ++i) {
                if (a[v][i] == 1 && !c[i]) {
                    q.add(i);
                    c[i] = true;
                }
            }
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        } 

        return answer;
    }

    /**
     * DFS - 인접리스트 사용
     * @param a  인접리스트
     * @param c  방문여부
     * @param v  탐색을 시작할 정점 번호
     * @param list  DFS 결과를 담을 리스트
     */
    public static void dfs(ArrayList<Integer>[] a, boolean[] c, int v, ArrayList<Integer> list) {
        if (c[v])
            return;
        
        c[v] = true;
        list.add(v);

        for (int vv : a[v]) {
            if (!c[vv]) {
                dfs(a, c, vv, list);
            }
        }
    }

    /**
     * BFS - 인접리스트 사용
     * @param a  인접리스트
     * @param c  방문여부
     * @param v  탐색을 시작할 정점 번호
     * @return
     */
    public static int[] bfs(ArrayList<Integer>[] a, boolean[] c, int v) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        q.add(v);
        c[v] = true;

        while(!q.isEmpty()) {
            v = q.poll();
            list.add(v);
            for (int vv : a[v]) {
                if (!c[vv]) {
                    q.add(vv);
                    c[vv] = true;
                }
            }
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        } 

        return answer;
    }
}