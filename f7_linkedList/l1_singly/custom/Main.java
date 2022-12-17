package com.f7_linkedList.l1_singly.custom;

import com.f7_linkedList.l1_singly.custom.LL;

public class Main {
    public static void main(String[] args) {
        mergeTwoSortedLists();
    }
    public static void mergeTwoSortedLists(){
        LL l1 = new LL();
        LL l2 = new LL();
        l1.add(1);
        l1.add(3);
        l1.add(7);
        l2.add(1);
        l2.add(3);
        l2.add(4);
        l2.add(8);
    }
    public static void practice(){
        LL list = new LL();
        System.out.println("Add linear");
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        System.out.println(list);

        System.out.println("Add at first index");
        list.addFirst(0);
        System.out.println(list);

        System.out.println("Add at any index");;
        list.add(1,2);
        list.add(4,1);
        System.out.println(list);

        System.out.println("Linear Remove");
        list.remove(9);
        System.out.println(list);

        System.out.println("Remove at any index");
        System.out.println(list.removeAt(3));
        System.out.println(list);

        System.out.println("Remove at first index");
        System.out.println(list.removeFirst());
        System.out.println(list);

        System.out.println("Remove at Last index");
        System.out.println(list.removeLast());
        System.out.println(list);

        System.out.println("Contains an element");
        System.out.println(list.contains(8));

        System.out.println("Reverse a linkedlist");
        list.reverse();
        System.out.println(list);

        System.out.println("Middle node in linked list");
        System.out.println("For odd size list");
        System.out.println(list);
        System.out.println(list.middleNode());
        System.out.println("Even size list  ");
        list.removeFirst();
        System.out.println(list);
        System.out.println(list.middleNode());

        System.out.println("Size of linked list");
        list.size();
    }

}
