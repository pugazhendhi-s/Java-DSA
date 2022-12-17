package com.f7_linkedList.Problems.sll;

public class Main {
    public static void main(String[] args) {
        LL l1 = new LL();
        l1.add(2);
        l1.add(4);
        l1.add(3);

        LL l2 = new LL();
        l2.add(5);
        l2.add(6);
        l2.add(4);

        LL l3 = new LL();
        l3.addTwoNumbers(l1, l2);
        System.out.println(l3);
    }
}
