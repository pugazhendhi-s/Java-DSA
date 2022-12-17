package com.f9_queueStack.queue.custom;

import java.util.Arrays;

public class CustomQueue {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;
    int end = 0;
    public CustomQueue(){
        this(DEFAULT_SIZE);  // calling below constructor by passing default size..
    }
    public CustomQueue(int size){
        this.data = new int[size];
    }
    public boolean isFull(){
        return end == data.length-1; // end at last index
    }
    public boolean isEmpty(){
        return end == -1;
    }
    public boolean add(int item){
        if(isFull()) {
            return false;
        }
        data[end++] = item;
        return true;
    }
    public int remove() throws Exception {
        if(isEmpty())
            throw new Exception("Queue is Empty..");
        int removed = data[0];
        // shift elements to left side because first element is deleted
        if (end - 1 >= 0) // atleast two elements
            System.arraycopy(data, 1, data, 0, end - 1);
        end--;
        return removed;
    }
    public int front() throws Exception {
        if(isEmpty())
            throw new Exception("Queue is Empty..");
        return data[0];
    }
    public void display(){
        for (int i = 0; i < end; i++) {
            System.out.print(data[i]+ " <- ");
        }
        System.out.println("END");
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
