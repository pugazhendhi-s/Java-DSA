package com.f9_queueStack.stack.leetCode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int top;
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q1.add(x);
        top = x;
    }

    public int pop() {
        while (q1.size() > 1){
            top = q1.remove();
            q2.add(top);
        }
        int popped = q1.remove();
        while (!q2.isEmpty()) {
            q1.offer(q2.poll());
        }
        return popped;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
    static class MyStack1{
        private final Queue<Integer> queue;
        public MyStack1(){
            queue = new LinkedList<>();
        }
        public void push(int x){
            queue.add(x);
            int size = queue.size();
            while (size-- > 1)
                queue.add(queue.remove());
        }
        public int pop(){
            return queue.remove();
        }
        public int top(){
            return queue.peek();
        }
        public boolean empty(){
            return queue.isEmpty();
        }
    }
}
