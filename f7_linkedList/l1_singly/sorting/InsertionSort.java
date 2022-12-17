package com.f7_linkedList.l1_singly.sorting;

public class InsertionSort {
    private static class ListNode{
        int val;
        ListNode next;

        public ListNode() {}
        public ListNode(int val){
            this.val = val;
        }
        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }
    private ListNode head;
    private ListNode tail;
    private int size;

    public InsertionSort(){
        this.size = 0;
    }
    public void addFirst(int val){
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
        if (tail == null)
            tail = head;
        size++;
    }
    public void add(int val){
        if(tail == null)
            addFirst(val);
        else {
            ListNode node = new ListNode(val);
            tail.next = node;
            tail = node;
            size++;
        }
    }
    public void add(int index, int val){
        if(index == 0)
            addFirst(val);
        else if(index == size)
            add(val);
        else {
            ListNode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            //ListNode<int> node = new ListNode<int>(val, temp.next);
            //temp.next = node;
            // below is inline , temp.next is directly assign to node which we are adding
            temp.next = new ListNode(val, temp.next);
            size++;
        }
    }
    public ListNode getListNode(int index){
        ListNode node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public void insertionSort(){
        head = insertionSort(head);
    }

    private ListNode insertionSort(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode next;
        ListNode prev = dummy;
        while (head != null){
            next = head.next;
            prev = (prev.val > head.val) ? dummy : prev;
            while (prev.next != null && prev.next.val < head.val){
                prev = prev.next;
            }
            head.next = prev.next;
            prev.next = head;
            head = next;
        }
        return dummy.next;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        ListNode temp = head;
        while (temp.next != null){
            builder.append(temp.val).append(", ");
            temp = temp.next;
        }
        builder.append(temp.val).append("]");
        return builder.toString();
    }
}
