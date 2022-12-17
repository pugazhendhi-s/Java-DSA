package com.f7_linkedList.l1_singly.sorting;

public class MainMerge {
    public static void main(String[] args) {
        MergeSort ms1 = new MergeSort();
        ms1.add(1);
        ms1.add(5);
        MergeSort ms2 = new MergeSort();
        ms2.add(-933);
        ms2.add(-3);
        ms2.add(4);
        MergeSort ms3 = new MergeSort();
        ms3.add(-2);
        ms3.add(6);
        MergeSort ms4 = new MergeSort();
        ms4.add(-92);
        ms4.add(-7);


        MergeSort ms = new MergeSort();
        ms.mergeKLists(ms1, ms2, ms3, ms4);
        System.out.println(ms);
    }
}
