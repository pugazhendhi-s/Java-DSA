package com.f9_queueStack.stack.custom;

import java.util.Stack;

public class MinStack {
    private static class Node{
        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    private Node head;

    public void push(int val){
        if(head == null)
            head = new Node(val, val, null);
        else{
            head = new Node(val, Math.min(val, head.min), head);
        }
    }
    public void pop(){
        head = head.next;
    }
    public int top(){
        return head.val;
    }
    public int getMin(){
        return head.min;
    }
}
class MinStackI{
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();
    public void push(int val){
        if(val <= min){
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }
    public void pop(){
        int peek = stack.pop();
        if(peek == min)
            min = stack.pop(); // double pop because, at min value we push two values;
    }
    public int top(){
        return stack.peek();
    }
    public int getMin(){
        return min;
    }
}
class MinStackII{
    Stack<Integer> stack, min;
    public MinStackII(){
        stack = new Stack<>();
        min = new Stack<>();
    }
    public void push(int val){
        stack.push(val);
        if(min.empty()) min.push(val);
        else if(val <= min.peek()) min.push(val);
    }
    public void pop(){
        int popped = stack.pop();
        if(!min.empty() && popped == min.peek())
            min.pop();
    }
    public int top(){
        return stack.peek();
    }
    public int getMin(){
        return min.peek();
    }
}
