import java.util.*;

class Node {
    int id;
    int x, y;
    Node left;
    Node right;

    Node (int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static int result_idx; // 전위 후위 순회 구할 시 사용

    public static void main(String[] args) {
        int[][] result = solution(new int[][] { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, 
            { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 }, { 2, 2 } } );

        for (int i = 0; i < result.length; ++i) {
            System.out.println();
            for (int j = 0; j < result[0].length; ++j)
                System.out.print(result[i][j] + " ");
        }
    }

    // 트리에 노드 추가
    static void addNode(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null)
                parent.left = child;
            else
                addNode(parent.left, child);
        } 
        else {
            if (parent.right == null)
                parent.right = child;
            else
                addNode(parent.right, child);
        }
    }

    // 전위 순회 코드
    static void preorder(int[][] answer, Node node) {
        if (node == null) return;

        answer[0][result_idx++] = node.id;
        preorder(answer, node.left);
        preorder(answer, node.right);
    }

    // 후위 순회 코드
    static void postorder(int[][] answer, Node node) {
        if (node == null) return;

        postorder(answer, node.left);
        postorder(answer, node.right);
        answer[1][result_idx++] = node.id;
    }

    public static int[][] solution(int[][] nodeinfo) {
        List<Node> Nodes = new ArrayList<Node>();
        int nodeSize = nodeinfo.length;
        int[][] answer = new int[2][nodeSize];

        for (int i = 0; i < nodeSize; ++i)
            Nodes.add(new Node( i + 1, nodeinfo[i][0], nodeinfo[i][1] ));

        // y가 큰 것 순으로 정렬. 만약 y값이 같을 시 x값이 작은 순으로 정렬
        Nodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if ( a.y == b.y )
                    return a.x - b.x;
                else 
                    return b.y - a.y;
            }
        });

        // y값이 가장 큰 것이 루트 노드.
        Node root = Nodes.get(0);

        // 트리 생성
        for (int i = 1; i < nodeSize; ++i)
            addNode(root, Nodes.get(i));
        

        preorder(answer, root);
        result_idx = 0;
        postorder(answer, root);
        return answer;
    }
}