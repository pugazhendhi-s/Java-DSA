package com.ff1_nonlinear.heaps;

import java.util.*;

public class HeapSort {

    public static void main(String[] args) {

        int[] nums = {6,5,3,2,8,10,9};
        int k = 3;
        nearlySorted(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public static int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        while (maxHeap.size() > 1) {
            int x = maxHeap.poll();
            int y = maxHeap.remove();  // poll doesn't throw any exception

            if (x - y > 0) {
                maxHeap.offer(x - y);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(map.entrySet());

        int[] res = new int[k];
        int i = 0;

        while (!maxHeap.isEmpty() && i != k) {
            res[i++] = maxHeap.poll().getKey();
        }
        return res;
    }
    public static void nearlySorted(int []nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(k+1);

        for (int i = 0; i <= k; i++) {
            pq.offer(nums[i]);
        }
        int index = 0;
        for (int i = k+1; i < nums.length; i++) {
            nums[index++] = pq.remove();
            pq.offer(nums[i]);
        }
        while (!pq.isEmpty())
            nums[index++] = pq.remove();
    }
    public static void myHeap() {

        Heaps heaps = new Heaps();
        heaps.push(1);
        heaps.push(2);
        heaps.push(3);
        heaps.push(4);
        heaps.push(5);
        heaps.push(6);
        System.out.println(heaps);
        heaps.sort();
        System.out.println(heaps);
        heaps.pop();
        heaps.pop();
        System.out.println(heaps);
    }

    public static void example() {
        int[] heap = {7, 3, 2, 6, 1, 4, 9, 5};
        heapSort(heap);
        System.out.println(Arrays.toString(heap));
    }

    private static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void heapify(int[] heap, int curr, int size) {

        int largest = curr; // curr as parent
        int left = 2 * curr + 1;
        int right = 2 * curr + 2;


        if (left < size && heap[left] > heap[curr])
            largest = left;

        if (right < size && heap[right] > heap[largest])
            largest = right;

        if (largest != curr) {
            swap(heap, curr, largest);
            heapify(heap, largest, size);
        }
    }

    public static void heapSort(int[] heap) {

        int size = heap.length;

        for (int i = (size / 2) - 1; i >= 0; i--)
            heapify(heap, i, size); // building heap

        for (int i = size - 1; i > 0; i--) {
            swap(heap, 0, i);
            heapify(heap, 0, i);
        }
    }

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int val : nums) {
            pq.offer(val);
        }
        while (k-- != 1) {
            pq.poll();
        }
        return pq.peek();
    }

}
