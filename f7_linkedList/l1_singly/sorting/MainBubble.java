package com.f7_linkedList.l1_singly.sorting;

public class MainBubble {
    public static void main(String[] args) {
        BubbleSort list = new BubbleSort();
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(6);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(0);
        list.add(3);
        list.bubbleSort();
        System.out.println(list);
    }
}
