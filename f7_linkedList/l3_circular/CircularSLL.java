package com.f7_linkedList.l3_circular;


public class CircularSLL<T> {
    private static class Node<T>{
        T data;
        Node<T> next;
        public Node(T data){
            this.data = data;
        }
        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    public CircularSLL(){
        this.head = null;
        this.tail = null;
    }
    public void addFirst(T data){
        Node<T> node = new Node<>(data);
        if(head == null){
            head = node;
            tail = node;
        }
        else {
            node.next = head;
            tail.next = node;
            head = node;
        }
    }
    public void add(T data){
        Node<T> node = new Node<>(data);
        if(head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.next = head;
        tail = node;
    }
    public void add(int index, T data){
        Node<T> node = new Node<>(data);
        Node<T> temp = getNode(index);
        if(temp == tail){
            node.next = head;
            tail.next = node;
            tail = node;
        }
        else if(temp == head){
            node.next = head;
            tail.next = node;
            head = node;
        }
        else{
            node.next = temp.next;
            temp.next = node;
        }
    }


    private Node<T> getNode(int index){
        Node<T> node = head;
        for (int i = 0; i < index-1; i++) {
            node = node.next;
        }
        return node;
    }
    private Node<T> findPrev(T key){ // assume key must contain
        Node<T> temp = head;
        while (temp.next.data != key){
            temp = temp.next;
        }
        return temp;
    }
    public T removeFirst(){
        T removed = head.data; // head != null;
        if(head == tail){
            head = tail = null;
        }
        else {
            head = head.next;
            tail.next = head;
        }
        return removed;
    }
    public T remove(){
        T removed = tail.data;
        if(head == tail){
            head = tail = null;
        }
        else {
            Node<T> temp = head;
            while (temp.next != tail){
                temp = temp.next;
            }
            temp.next = tail.next;
            tail = temp;
        }
        return removed;
    }
    public T remove(int index){
        if(index == 0)
            return removeFirst();
        Node<T> temp = getNode(index);
        if(temp.next == tail)
            return remove();
        T removed = temp.next.data;
        temp.next = temp.next.next;
        return removed;
    }
    public boolean remove(T key){
        if(head.data == key){
            removeFirst();
            return true;
        }
        if(tail.data == key){
            remove();
            return true;
        }
        Node<T> temp = findPrev(key);
        temp.next = temp.next.next;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<T> temp = head;
        do {
            builder.append(temp.data).append(", ");
            temp = temp.next;
        }while (temp != tail);
        builder.append(temp.data).append("]");
        return builder.toString();
    }
}
