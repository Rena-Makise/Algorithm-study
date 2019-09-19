import java.util.*;

/**
 * 힙 정렬
 * 완전 이진 트리를 기본으로 하는 힙 자료구조를 기반
 * 최악, 최선, 평균 모두 O(nlogn) 
 * 힙 정렬은 가장 크거나 작은 값을 구할 때, 최대 k 만큼 떨어진 요소들을 정렬할 때 유용
 */
class Solution {
    public static void main(String[] args) {
        int[] array = { 230, 10, 60, 550, 40, 220, 20 };

        heapSort(array);

        for (int v : array) {
            System.out.print(v + " ");
        }
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; --i) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; --i) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    public static void heapify(int array[], int n, int i) {
        int p = i;
        int l = i * 2 + 1;
        int r = i * 2 + 2;

        if (l < n && array[p] < array[l]) {
            p = l;
        }

        if (r < n && array[p] < array[r]) {
            p = r;
        }

        if (i != p) {
            swap(array, p, i);
            heapify(array, n, p);
        }
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}