package com.f9_queueStack.stack.custom;

import com.f9_queueStack.stack.custom.CustomStack;
import com.f9_queueStack.stack.custom.DynamicStack;
import com.f9_queueStack.stack.custom.StackException;

public class StackMain {
    public static void main(String[] args) throws StackException {
        CustomStack ds = new DynamicStack(1);
        ds.push(5);
        System.out.println(ds.peek());
        ds.push(3);
        System.out.println(ds.peek());
        ds.push(7);
        System.out.println(ds);
        System.out.println(ds.peek());
        ds.push(1);
        System.out.println(ds.peek());
        ds.push(0);
        System.out.println(ds.peek());
        ds.push(9);
        System.out.println(ds.peek());
        System.out.println("get value :"+ds.get(2));
        System.out.println("size : "+ds.size());
        System.out.println("pop ;"+ds.pop());
        System.out.println("peek :"+ds.peek());
        System.out.println("empty : "+ds.empty());
        System.out.println(ds);
    }
}
