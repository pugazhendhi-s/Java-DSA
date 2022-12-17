package com.f9_queueStack.queue.leetCode;

import java.util.*;

public class Easy {
    public static void main(String[] args) {

        modifyQueue();
    }
    public static void modifyQueue(){
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        queue = modifyQueue(queue, 3);
        System.out.println(queue);
        // addAll.(Arrays.asList(1, 2, 3, 4, 5));
    }
    private static Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        while (k-- > 0){
            stack.push(q.poll());
        }
        while (!stack.empty()){
            queue.add(stack.pop());
        }
        while (!q.isEmpty()){
            queue.add(q.poll());
        }
        return queue;
    }
}
