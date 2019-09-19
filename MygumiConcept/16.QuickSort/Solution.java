import java.util.*;

/**
 * 퀵 소트
 * 분할 정복 방법을 통해 구현
 * 최악의 경우 O(n^2), 평균적으로 O(nlogn)
 */
class Solution {
    public static void main(String[] args) {
        int[] array = { 80, 70, 60, 50, 40, 30, 20 };
        quicksort(array, 0, array.length - 1);

        for (int v : array) {
            System.out.print(v + " ");
        }
    }

    public static int partition(int[] array, int left, int right) {
        // 여기서는 피벗을 중간요소로 선택
        // 이를 통해 역순으로 되어있을때 최악의 경우인 O(n^2)를 피할 수 있다!
        int mid = (left + right) / 2;
        swap(array, left, mid);

        int pivot = array[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (pivot < array[j]) {
                j--;
            }

            while (i < j && pivot >= array[i]) {
                i++;
            }
            swap(array, i, j);
        }
        array[left] = array[i];
        array[i] = pivot;
        return i;
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    public static void quicksort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pi = partition(array, left, right);

        quicksort(array, left, pi - 1);
        quicksort(array, pi + 1, right);
    }
}