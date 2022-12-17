package com.f7_linkedList.l2_doubly;


public class Main {
    public static void main(String[] args) {

        DLL<Integer> list = new DLL<>();

        list.addFirst(3);

        list.addFirst(9);
        System.out.println(list);

        list.add(99);
        list.add(21);
        System.out.println(list);

        list.add(0, 23);
        list.add(1, 56);
        System.out.println(list);

        list.addAfter(99, 88);
        list.addAfter(56, 0);
        System.out.println(list);


        System.out.println(list.remove());
        System.out.println(list);

        System.out.println(list.removeFirst());
        System.out.println(list);

        System.out.println(list.remove(5));
        System.out.println(list);

        System.out.println(list.remove(0));
        System.out.println(list);

        System.out.println("reverse");
        list.reverse();
        System.out.println(list);


        System.out.println(list.remove(Integer.valueOf(0)));
        System.out.println(list);

    }
}
