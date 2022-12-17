package com.f7_linkedList.l1_singly.sorting;

public class InsertionMain {
    public static void main(String[] args) {
        InsertionSort ll = new InsertionSort();
        ll.add(5);
        ll.add(6);
        ll.add(2);
        ll.add(1);
        ll.add(4);
        System.out.println(ll);
        ll.insertionSort();
        System.out.println(ll);
    }
}
