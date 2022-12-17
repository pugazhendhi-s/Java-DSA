package com.f7_linkedList.l1_singly.sorting;

public class MergeSort {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {}
        public ListNode(int val){
            this.val = val;
            next = null;
        }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }
    private ListNode head;
    private ListNode tail;
    private int size;

    public MergeSort(){this.size = 0;}
    public void addFirst(int data){
        ListNode node = new ListNode(data);
        node.next = head;
        head = node;
        if (tail == null)
            tail = head;
        size++;
    }
    public void add(int data){
        if(tail == null)
            addFirst(data);
        else {
            ListNode node = new ListNode(data);
            tail.next = node;
            tail = node;
            size++;
        }
    }
    public void add(int index, int data){
        if(index == 0)
            addFirst(data);
        else if(index == size)
            add(data);
        else {
            ListNode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            //ListNode<int> node = new ListNode<int>(data, temp.next);
            //temp.next = node;
            // below is inline , temp.next is directly assign to node which we are adding
            temp.next = new ListNode(data, temp.next);
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
    public void mergeSort(){
        head = sortList(head);
    }
    public ListNode sortList(ListNode curHead){
        if(curHead == null || curHead.next == null)
            return curHead;
        ListNode mid = getMid(curHead);
        ListNode left = sortList(curHead);
        ListNode right = sortList(mid);
        return merge(left, right);
    }
    public ListNode merge(ListNode n1, ListNode n2){
        ListNode curHead = new ListNode(); // assigning empty node, val = 0;
        ListNode curTail = curHead;
        while (n1 != null && n2 != null){
            if(n1.val <= n2.val){
                curTail.next = n1;
                curTail = curTail.next;
                n1 = n1.next;
            }
            else{
                curTail.next = n2;
                curTail = curTail.next;
                n2 = n2.next;
            }
        }
        curTail.next = n1 != null ? n1 : n2;
        return curHead.next; // at first, we're assigning one node head, that is not needed
    }
    public ListNode getMid(ListNode curHead){
        ListNode slow = curHead;
        ListNode fast = curHead.next.next; // directly I take from this because, I already check condition curHead.next != null
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        return mid;
    }

    public void mergeKLists(MergeSort ms1, MergeSort ms2, MergeSort ms3, MergeSort ms4){
        ListNode n1 = ms1.head;
        ListNode n2 = ms2.head;
        ListNode n3 = ms3.head;
        ListNode n4 = ms4.head;
        ListNode[] nodes = {n1, n2, n3, n4};
        head = mergeKList(nodes);
    }
    private  ListNode mergeKList(ListNode[] nodes) {
        if(nodes.length == 0)
            return null;
        int interval = 1;
        while (interval < nodes.length){
            for (int i = 0; (i+interval) < nodes.length; i = i+interval*2) {
                nodes[i] = merge(nodes[i], nodes[i+interval]);
            }
            interval *= 2;
        }
        return nodes[0];
    }
    private  ListNode mergeKLists(ListNode[] nodes) {
        if(nodes.length == 0)
            return null;
        ListNode curHead = new ListNode(Integer.MIN_VALUE);
        int i = 0;
        while (i+1 < nodes.length){
            ListNode n1 = nodes[i];
            ListNode n2 = nodes[i+1];
            if(n1 == null){
                i++;
                continue;
            }
            ListNode Head = merge(n1, n2);
            if(curHead.val == Integer.MIN_VALUE)
                curHead = merge(curHead.next, Head);
            else{
                curHead = merge(curHead, Head);
            }
            i += 2;
        }
        if(i != nodes.length){
            curHead = merge(curHead, nodes[i]);
        }
        return (curHead.val == Integer.MIN_VALUE) ? curHead.next : curHead;
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
