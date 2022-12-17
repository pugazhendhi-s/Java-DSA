package com.f9_queueStack.stack.custom;

public class CustomStack {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;
    private int top = 0;

    public CustomStack(){
        this(DEFAULT_SIZE);
    }
    public CustomStack(int size){
        this.data = new int[size];
    }
    public boolean push(int item){
        if(isFull()){
            System.out.println("Stack is full!!..");
            return false;
        }
        data[top++] = item;
        return true;
    }
    public int pop() throws StackException {
        if(isEmpty())
            throw new StackException("Cannot pop from an empty stack!!");
        return data[--top];
    }
    public int peek()throws StackException{
        if(isEmpty())
            throw new StackException("Cannot peek from an empty stack!!");
        return data[top-1];
    }
    public int size(){
        return top;
    }
    public int get(int index) throws StackException{
        if(index < 0 || index >= size()) {
            throw new StackException("Index out of bounds");
        }
        return data[index];
    }
    public boolean isFull(){
        return top == data.length-1;// top is at last index;
    }
    private boolean isEmpty(){
        return top == -1;
    }
    public boolean empty(){
        return top == -1;
    }
}

