package com.f9_queueStack.stack.leetCode.medium;
import java.util.*;

class StockSpanner {

    private final Stack<int[]> stack;
    public StockSpanner() {
        this.stack = new Stack<>();
    }

    public int next(int price) {

        int span = 1;
        while ( !stack.empty() && stack.peek()[0] <= price) {
            span += (stack.pop()[1]);
        }
        stack.push(new int[] {price, span});
        return span;
    }

    // brute force
   /*  private int nextBf(int price) {
        int span = 1;
        Queue<Integer> queue = new LinkedList<>();
        while ( !stack.empty() && stack.peek() <= price) {
            queue.offer(stack.pop());
            span++;
        }
        while ( !queue.isEmpty()) {
            stack.push(queue.poll());
        }
        stack.push(price);

        return span;
    }

    */
}