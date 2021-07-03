package study;

import textbook.UnderflowException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * @author :Xuan
 * @date :Create in 2021/3/30 10:37
 * @description 第六章 优先队列（堆）
 */
public class Heap {
    static class MyBinaryHeap<T extends Comparable<? super T>> {
        private static final int DEFAULT_CAPACITY = 10;
        private int size;
        private T[] heap;

        public MyBinaryHeap() {
            new MyBinaryHeap<>(DEFAULT_CAPACITY);
        }

        public MyBinaryHeap(int capacity) {
            heap = (T[]) new Comparable[capacity + 1];
        }

        public void insert(T t) {
            if (size == heap.length - 1)
                enlargeArray((size << 1) + 3);
            int hole = ++size;
            for (heap[0] = t; t.compareTo(heap[hole >> 1]) < 0; hole >>= 1) {
                heap[hole] = heap[hole >> 1];
            }
            heap[hole] = t;
        }

        public T findMin() {
            if (isEmpty())
                throw new NoSuchElementException();

            return heap[1];
        }

        public T deleteMin() {
            if (isEmpty())
                throw new NoSuchElementException();

            T minItem = findMin();
            heap[1] = heap[size--];
            percolateDown(1);

            return minItem;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void makeEmpty() {
            for (int i = 0; i < size; i++) {
                heap[i] = null;
            }
            size = 0;
        }

        private void percolateDown(int hole) {
            int child;
            T temp = heap[hole];

            while ((hole << 1) < size) {
                child = hole << 1;

                if (child != size && heap[child + 1].compareTo(heap[child]) < 0)
                    child++;

                if (heap[child].compareTo(temp) < 0)
                    heap[hole] = heap[child];
                else break;

                hole = child;
            }
            heap[hole] = temp;
        }

        private void enlargeArray(int newSize) {
            T[] newHeap = (T[]) new Comparable[newSize];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }

        public MyBinaryHeap(T[] items) {
            size = items.length;
            heap = (T[]) new Comparable[(size + 2) * 11 / 10];

            int i = 1;
            for (T item : items)
                heap[i++] = item;
            buildHeap();
        }

        private void buildHeap() {
            for (int i = size >> 1; i > 0; i--)
                percolateDown(i);
        }

    }
}
