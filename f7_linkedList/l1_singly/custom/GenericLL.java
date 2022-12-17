package com.f7_linkedList.l1_singly.custom;

public class GenericLL<T> {

    private static class Node<T>{
        T data;
        Node<T> next;

        public Node() {}
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
    private int size;

    public GenericLL(){
        this.size = 0;
    }
    public void addFirst(T data){
        Node<T> node = new Node<>(data);
        node.next = head;
        head = node;
        if (tail == null)
            tail = head;
        size++;
    }
    public void add(T data){
        if(tail == null)
            addFirst(data);
        else {
            Node<T> node = new Node<>(data);
            tail.next = node;
            tail = node;
            size++;
        }
    }
    public void add(int index, T data){
        if(index == 0)
            addFirst(data);
        else if(index == size)
            add(data);
        else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            //Node<T> node = new Node<T>(data, temp.next);
            //temp.next = node;
            // below is inline , temp.next is directly assign to node which we are adding
            temp.next = new Node<>(data, temp.next);
            size++;
        }
    }

    // remove
    public T removeFirst(){
        T removed = head.data;
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return removed;
    }
    /*public T removeLast(){
        if(head != null && head.next == null){
            return removeFirst();
        }
        else {
            Node<T> temp = head;
            Node<T> prev = null;
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = null;
            tail = prev;
            size--;
            return temp.data;
        }
    }*/
    public T removeLast(){
        if (size <= 1){
            return removeFirst();
        }
        else{
            // previous node of last node
            Node<T> prev = getNode(size - 2); // second last node
            T removed = tail.data;
            tail = prev;
            tail.next = null;
            size--;
            return removed;
        }
    }
    public T remove(int index){
        if(index == 0)
            return removeFirst();
        if(index == size-1)
            return removeLast();
        Node<T> prev = getNode(index-1);
        T data = prev.next.data;
        prev.next = prev.next.next;
        size--;
        return data;
    }
    public Node<T> getNode(int index){
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public Node<T> findNode(T key){
        Node<T> node = head;
        while (node != null){
            if(node.data == key)
                return node;
            node = node.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<T> temp = head;
        while (temp.next != null){
            builder.append(temp.data).append(", ");
            temp = temp.next;
        }
        builder.append(temp.data).append("]");
        return builder.toString();
    }
}
