package com.f9_queueStack.queue.custom;

import com.f9_queueStack.queue.custom.CircularQueue;

public class DynamicQueue extends CircularQueue {

    public DynamicQueue(){
        super();
    }
    public DynamicQueue(int size){
        super(size);
    }

    @Override
    public boolean add(int item) {
        if(this.isFull()){
            int[] temp = new int[data.length*2];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[(front+i) % data.length];
            }
            front = 0;
            rear = data.length;
            data = temp;
        }
        return super.add(item);
    }
}
