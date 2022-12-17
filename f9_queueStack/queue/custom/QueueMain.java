package com.f9_queueStack.queue.custom;

import com.f9_queueStack.queue.custom.CircularQueue;

public class QueueMain {
    public static void main(String[] args) throws Exception{
        CircularQueue queue = new CircularQueue(5);
        queue.add(49);
        queue.add(48);
        queue.add(41);
        queue.add(44);
        queue.add(4);
        queue.display();
        System.out.println(queue);
        System.out.println(queue.remove());
        queue.add(33);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        queue.display();
    }
}
