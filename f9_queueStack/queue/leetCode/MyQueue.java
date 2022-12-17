package com.f9_queueStack.queue.leetCode;
import java.util.*;

public class MyQueue {

    private int front;
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        if(s1.empty())
            front = x;
        s1.push(x);
    }
    
    public int pop() {
        if(s2.empty()){
           while(!s1.empty()) 
               s2.push(s1.pop());
        }
        return s2.pop();
    }
    
    public int peek() {
        if(!s2.empty())
            return s2.peek();
        return front;
    }
    
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}