package com.f9_queueStack.stack.leetCode.medium;

public class CustomStack {

    private final int[] data;
    private int top = 0;
    public CustomStack(int maxSize) {
        this.data = new int[maxSize];
    }
    
    public void push(int x) {
        if(top != data.length){
            data[top++] = x;
        }
    }
    
    public int pop() {
        if(top > 0)
            return data[--top];
        return -1;
    }
    
    public void increment(int k, int val) {
        for(int i = 0; i<k && i<top; i++){
            data[i] += val;
        }
    }
}