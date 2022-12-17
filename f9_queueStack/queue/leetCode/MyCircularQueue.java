package com.f9_queueStack.queue.leetCode;

public class MyCircularQueue {

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
        System.out.print(queue.enQueue(1)+" ");
        System.out.print(queue.enQueue(2)+" ");
        System.out.print(queue.enQueue(3)+" ");
        System.out.print(queue.enQueue(4)+" ");
        System.out.print(queue.Rear()+" ");
        System.out.print(queue.Front()+" ");
        System.out.print(queue.isFull()+" ");
        System.out.print(queue.deQueue()+" ");
        System.out.print(queue.enQueue(4)+" ");
        System.out.print(queue.Rear()+" ");
        queue.show();
    }
    private final int[] data;
    private int front = 0;
    private int rear = -1;
    private int size = 0;
    public MyCircularQueue(int k) {
        this.data = new int[k];
    }
    
    public boolean enQueue(int value) {
        if(isFull())
            return false;
        rear = (rear+1) % data.length;
        data[rear] = value;
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty())
            return false;
        front = (front+1) % data.length;
        size--;
        return true;
    }
    
    public int Front() {
        return isEmpty() ? -1 : data[front];
    }
    
    public int Rear() {
        return isEmpty() ? -1 : data[rear];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == data.length;
    }
    public void show(){
        int i = front;
        do{
            System.out.print(data[i]+" -> ");
            i++;
            i %= data.length;
        } while (i != rear);
        System.out.print("end\n");
    }
}