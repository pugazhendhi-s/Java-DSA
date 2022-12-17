package com.f9_queueStack.implementation;

public class QueueLinked {

    QueueNode front, rear;

    public void push(int a) {
        QueueNode node = new QueueNode(a);
        if (front == null) {
            front = node;
        }
        else {
            rear.next = node;
        }
        rear = node;
    }


    public int pop() {
        if (front == null)
            return -1;
        int removed = front.val;
        front = front.next;
        return removed;
    }
}
