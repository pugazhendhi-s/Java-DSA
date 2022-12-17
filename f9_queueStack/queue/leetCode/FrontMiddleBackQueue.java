package com.f9_queueStack.queue.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class FrontMiddleBackQueue {
    private final Deque<Integer> first, second;
    public FrontMiddleBackQueue(){
        first = new ArrayDeque<>();
        second = new ArrayDeque<>();
    }
    public void pushFront(int val) {
        if(first.size() > second.size())
            second.offerFirst(first.pollLast());
        first.offerFirst(val);
    }

    public void pushMiddle(int val) {
        if(first.size() > second.size())
            second.offerFirst(first.pollLast());
        first.offerLast(val);
    }

    public void pushBack(int val) {
        second.offerLast(val);
        if(second.size() > first.size())
            first.offerLast(second.pollFirst());

    }

    public int popFront() {
        if(first.size() == 0)
            return -1;
        if(first.size() == second.size())
            first.offerLast(second.pollFirst());
        return first.removeFirst();
    }

    public int popMiddle() {
        if(first.size() == 0)
            return -1;
        int mid = first.pollLast();
        if(first.size() < second.size())
            first.offerLast(second.pollFirst());
        return mid;
    }

    public int popBack() {
        if(first.size() == 0)
            return -1;
        if(first.size() > second.size())
            second.offerFirst(first.pollLast());
        return second.removeLast();
    }
    // queue by linked list
    static class FMBQueue{
        private final LinkedList<Integer> queue;

        public FMBQueue(LinkedList<Integer> queue) {
            this.queue = new LinkedList<>();
        }
        public void pushFront(int val) {
            queue.add(0, val);
        }

        public void pushMiddle(int val) {
            queue.add(queue.size()/2, val);
        }

        public void pushBack(int val) {
            queue.add(val);
        }

        public int popFront() {
            return queue.isEmpty() ? -1 : queue.remove(0);
        }

        public int popMiddle() {
            int midIndex = (queue.size() - 1) / 2; // we are deleting front most middle
            return queue.isEmpty() ? -1  : queue.remove(midIndex);
        }

        public int popBack() {
            return queue.isEmpty() ? -1 : queue.removeLast();
        }
    }
}