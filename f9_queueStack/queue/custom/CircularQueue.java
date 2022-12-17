package com.f9_queueStack.queue.custom;

public class CircularQueue {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;
    protected int rear = -1;
    protected int front = 0;
    private int size = 0;

    public CircularQueue(){
        this(DEFAULT_SIZE);  // calling below constructor by passing default size..
    }
    public CircularQueue(int size){
        this.data = new int[size];
    }
    public boolean isFull() {
        return size == data.length;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean add(int item){
        if(isFull()) {
            return false;
        }
        rear = (rear+1) % data.length;
        data[rear] = item;
        size++;
        return true;
    }
    public int remove() throws Exception {
        if(isEmpty())
            throw new Exception("Queue is Empty..");
        int removed = data[front];
        front = (front+1) % data.length;
        size--;
        return removed;
    }
    public int front() throws Exception {
        if(isEmpty())
            throw new Exception("Queue is Empty..");
        return data[front];
    }
    public void display(){
        if(isEmpty()){
            System.out.println("Queue is Empty..");
        }
        int i = front;
        do{
            System.out.print(data[i]+" -> ");
            i++;
            i %= data.length;
        } while (i != rear);
        System.out.println("rear");
    }
}
