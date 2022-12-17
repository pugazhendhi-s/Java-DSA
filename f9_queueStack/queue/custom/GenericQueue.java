package com.f9_queueStack.queue.custom;

public class GenericQueue<T> {
    protected Object[] data;
    private static final int DEFAULT_SIZE = 10;
    protected int front = 0;
    protected int end = 0;
    private int size = 0;

    public GenericQueue(){
        this(DEFAULT_SIZE);
    }
    public GenericQueue(int size) {
        this.data = new Object[size];
    }
    private boolean isFull(){
        return size == data.length;
    }
    private boolean isEmpty() { return size == 0; }

    public boolean add(T item){
        if(isFull()){
            Object[] temp = new Object[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[(i+front) % data.length];
            }
            front = 0;
            end = data.length;
            data = temp;
        }
        else {
            data[end++] = item;
            end %= data.length;
            size++;
        }
        return true;
    }
    public T removed() throws Exception{
        if(isEmpty()){
            throw new Exception("Queue is empty!..");
        }
        T removed = (T) data[front++];
        front %= data.length;
        size--;
        return removed;
    }
    public T front() throws Exception {
        if(isEmpty()){
            throw new Exception("Queue is empty!...");
        }
        return (T)data[front];
    }

}
