package com.ff1_nonlinear.heaps;

public class Heaps {

    private final static int DEFAULT_SIZE = 1001;
    private final int[] heap = new int[DEFAULT_SIZE];
    private int heapSize = 0;

    public boolean push(int val) {

        if (heapSize >= DEFAULT_SIZE) {
            System.out.println("Heap OverFlow");
            return false;
        }

        heap[heapSize] = val;
        percolateUp();
        heapSize++;
        return true;
    }
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private void percolateUp() {

        int curr = heapSize;
        while (curr > 0 && heap[curr] > heap[(curr-1)/2]) {
            swap(curr, (curr-1)/2);
            curr = (curr-1)/2;
        }
    }

    public int pop() {

        if (heapSize < 1) {
            System.out.println("Heap Under Flow");
            return -1;
        }
        int popped = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;

        heapify(0, heapSize);
        return popped;
    }

    private void heapify(int curr, int size) {

        while (2 * curr + 1 < size) {

            int left = 2 * curr + 1, right = 2 * curr + 2;

            int largest = (heap[left] > heap[right]) || (right == size) ? left : right;

            if (heap[largest] > heap[curr]) {
                swap(curr, largest);
                curr = largest;
            }
            else break;
        }

    }

    public void sort() {

        for (int i = (heapSize / 2) - 1; i >= 0; i--)
            heapify(i, heapSize); // building heap

        for (int i = heapSize - 1; i > 0; i--) {

            swap( 0, i);
            heapify(0, i);
        }
    }

    @Override
    public String toString() {
        if (heapSize == 0)
            return "[]";
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < heapSize - 1; i++) {
            builder.append(heap[i]).append(", ");
        }
        builder.append(heap[heapSize - 1]).append("]");

        return builder.toString();
    }
}
