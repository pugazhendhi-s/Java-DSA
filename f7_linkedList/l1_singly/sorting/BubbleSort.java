package com.f7_linkedList.l1_singly.sorting;

public class BubbleSort {
    private static class Node{
        int data;
        Node next;

        public Node() {}
        public Node(int data){
            this.data = data;
        }
        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }
    private Node head;
    private Node tail;
    private int size;

    public BubbleSort(){
        this.size = 0;
    }
    public void addFirst(int data){
        Node node = new Node(data);
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
            Node node = new Node(data);
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
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            //Node<int> node = new Node<int>(data, temp.next);
            //temp.next = node;
            // below is inline , temp.next is directly assign to node which we are adding
            temp.next = new Node(data, temp.next);
            size++;
        }
    }
    public Node getNode(int index){
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public void bubbleSort(){
        bubbleSort(size-1, 0);
    }

    private void bubbleSort(int row, int col) {
        if(row == 0)
            return;
        if(col < row){
            Node first = getNode(col);
            Node second = getNode(col+1);
            if(first.data > second.data){
                if(first == head){
                    head = second;
                    first.next = second.next;  // first.next = head.next
                    head.next = first; // second.next = first
                }
                else if(second == tail){
                    Node prev = getNode(col-1);
                    tail = first;
                    prev.next = second;
                    second.next = tail;
                    first.next = null; // tail.next = null;
                }
                else{
                    Node prev = getNode(col-1);
                    prev.next = second;
                    first.next = second.next;
                    second.next = first;
                }
            }
            bubbleSort(row, col+1);
        }
        else{
            bubbleSort(row -1, 0);
        }
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node temp = head;
        while (temp.next != null){
            builder.append(temp.data).append(", ");
            temp = temp.next;
        }
        builder.append(temp.data).append("]");
        return builder.toString();
    }
}
