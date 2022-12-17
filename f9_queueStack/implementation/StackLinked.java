package com.f9_queueStack.implementation;

public class StackLinked {

    StackNode top;

    public void push(int a) {
        StackNode node = new StackNode(a);
        if (top != null) {
            node.next = top;
        }
        top = node;
    }

    //Function to remove an item from top of the stack.
    public int pop() {
        if (top == null)
            return -1;
        int  removed = top.val;
        top = top.next;
        return removed;
    }
}
