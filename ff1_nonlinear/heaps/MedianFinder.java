package com.ff1_nonlinear.heaps;

import java.util.PriorityQueue;

public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder md = new MedianFinder();
        md.addNum(1);
        md.addNum(5);
        md.addNum(3);
        md.addNum(2);
        md.addNum(4);
        md.addNum(4);
        System.out.println(md.findMedian());
    }
    private final PriorityQueue<Integer> small, large;

    public MedianFinder() {
        small = new PriorityQueue<>((a, b) -> b -a);
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {

        if (small.size() == large.size()) {
            large.offer(num);
            small.offer(large.poll());
        }
        else {
            small.offer(num);
            large.offer(small.poll());
        }
    }
    
    public double findMedian() {

        if (small.size() == large.size())
            return (small.peek() + large.peek())/2.0;

        return small.peek();
    }
}