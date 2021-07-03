package study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/3/31 19:38
 * @description 第七章 排序
 */
public class Sort<T extends Comparable<? super T>> {
    Integer[] testArray = {5, 4, 3, 2, 1};

    //冒泡排序
    public void bubbleSort(T[] values) {
        int n = values.length;
        for (int i = 1; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n - i; j++) {
                if (values[j].compareTo(values[j + 1]) > 0) {
                    swap(values, j + 1, j);
                    flag = false;
                }
            }
            if (flag)
                break;
        }
    }

    private void swap(T[] values, int a, int b) {
        T temp = values[a];
        values[a] = values[b];
        values[b] = temp;
    }

    @Test
    public void testBubbleSort() {
        new Sort<Integer>().bubbleSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    //选择排序
    public void selectionSort(T[] values) {
        int n = values.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (values[minIndex].compareTo(values[j]) > 0)
                    minIndex = j;
            }
            swap(values, minIndex, i);
        }

    }

    @Test
    public void testSelectionSort() {
        new Sort<Integer>().selectionSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    //插入排序
    public void insertionSort(T[] values) {
        int n = values.length;
        insertionSort(values,0,n - 1);
    }

    private void insertionSort(T[] values, int left, int right) {
        int j;
        for (int i = left + 1; i <= right; i++) {
            T temp = values[i];
            for (j = i; j > left && temp.compareTo(values[j - 1]) < 0; j--) {
                values[j] = values[j - 1];
            }
            values[j] = temp;
        }
    }

    @Test
    public void testInsertionSort() {
        new Sort<Integer>().insertionSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }


    //希尔排序
    public void shellSort(T[] values) {
        int n = values.length;
        int j;
        for (int gap = n >> 1; gap > 0; gap >>= 1) {
            for (int i = gap; i < n; i++) {
                T temp = values[i];
                for (j = i; j >= gap && temp.compareTo(values[j - gap]) < 0; j -= gap)
                    values[j] = values[j - gap];
                values[j] = temp;
            }
        }
    }

    @Test
    public void testShellSort() {
        new Sort<Integer>().shellSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    //堆排序
    public void heapSort(T[] values) {
        int n = values.length;
        for (int i = (n >> 1) - 1; i >= 0; i--)
            percolateDown(values, i, n);
        for (int i = n - 1; i > 0; i--) {
            swap(values, 0, i);
            percolateDown(values, 0, i);
        }
    }

    private int leftChild(int i) {
        return (i << 1) + 1;
    }

    private void percolateDown(T[] a, int i, int n) {
        int child;
        T temp;
        for (temp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (temp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else break;
        }
        a[i] = temp;
    }

    @Test
    public void testHeapSort() {
        new Sort<Integer>().heapSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    //归并排序
    public void mergeSort(T[] values) {
        int n = values.length;
        T[] tempArray = (T[]) new Comparable[n];
        mergeSort(values, tempArray, 0, n - 1);
    }

    private void mergeSort(T[] values, T[] tempArray, int left, int right) {
        if (left < right) {
            int center = (left + right) >> 1;
            mergeSort(values, tempArray, left, center);
            mergeSort(values, tempArray, center + 1, right);
            merge(values, tempArray, left, center + 1, right);
        }
    }

    private void merge(T[] values, T[] tempArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (values[leftPos].compareTo(values[rightPos]) <= 0)
                tempArray[tempPos++] = values[leftPos++];
            else
                tempArray[tempPos++] = values[rightPos++];

        while (leftPos <= leftEnd)
            tempArray[tempPos++] = values[leftPos++];
        while (rightPos <= rightEnd)
            tempArray[tempPos++] = values[rightPos++];

        for (int i = 0; i < numElements; i++, rightEnd--)
            values[rightEnd] = tempArray[rightEnd];
    }

    @Test
    public void testMergeSort() {
        new Sort<Integer>().mergeSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    //快速排序
    public void quickSort(T[] values) {
        int n = values.length;
        quickSort(values, 0, n - 1);
    }
    private T medianOfThree(T[] values, int left, int right) {
        int center = (left + right) >> 1;

        if (values[center].compareTo(values[left]) < 0)
            swap(values, left, center);
        if (values[right].compareTo(values[left]) < 0)
            swap(values, left, right);
        if (values[right].compareTo(values[center]) < 0)
            swap(values, center, right);

        swap(values, center, right - 1);

        return values[right - 1];
    }
    private static final int CUTOFF = 3;
    private void quickSort(T[] values, int left, int right) {
        if (left + CUTOFF <= right) {
            T pivot = medianOfThree(values, left, right);

            int i = left;
            int j = right;

            for (; ; ) {
                while (values[++i].compareTo(pivot) < 0) ;
                while (values[--j].compareTo(pivot) > 0) ;
                if (i < j)
                    swap(values, i, j);
                else break;
            }

            swap(values, i, right - 1);

            quickSort(values, left, i - 1);
            quickSort(values, i + 1, right);
        } else insertionSort(values, left, right);
    }

    @Test
    public void testQuickSort() {
        new Sort<Integer>().quickSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    //基于快速排序的 快速选择 获得数组中第k小的元素 完成后索引为 k - 1 的元素即是第k小元
    public void quickSelect(T[] values, int left, int right, int k){
        if (left + CUTOFF <= k){
            T pivot = medianOfThree(values,left,right);

            int i = left;
            int j = right - 1;

            for (; ; ) {
                while (values[++i].compareTo(pivot) < 0) ;
                while (values[--j].compareTo(pivot) > 0) ;
                if (i < j)
                    swap(values, i, j);
                else break;
            }

            swap(values, i, right - 1);

            if (k <= i)
                quickSelect(values,left,i - 1,k);
            else if (k > i + 1)
                quickSelect(values,i + 1,right,k);

        }else insertionSort(values,left,right);
    }
}
