package com.f7_linkedList.Problems.lceasy;

public class Main {
    public static void main(String[] args) {
        LCEasy list = new LCEasy();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(2);
        System.out.println(list);
        list.nodeBetweenCriticalPoints();
        System.out.println(list);

    }

}
