package com.f7_linkedList.l3_circular;

public class CircularDLL<T> {
    private static class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data){
            this.data = data;
        }

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public void add(T data){ // addLast also same
        if (head == null)
            addFirst(data);
        else{
            Node<T> node = new Node<>(data);
            node.next = head;
            head.prev = node;
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
    }
    public void addFirst(T data){
        Node<T> node = new Node<>(data);
        if(head == null){
            head = node;
            tail = node;
        }
        else {
            node.next = head;
            head.prev = node;
            node.prev = tail;
            tail.next = node;
            head = node;
        }
    }

    public void add(int index, T data){
        if(index == 0)
            addFirst(data);
        else {
            Node<T> node = new Node<>(data);
            Node<T> temp = getNode(index);
            if (temp == tail)
                add(data);
            else {
                node.next = temp.next;
                node.prev = temp;
                temp.next = node;
                node.next.prev = node;
            }
        }
    }
    public Node<T> findNode(T key){
        Node<T> node = head;
        do {
            if(node.data == key)
                return node;
            node = node.next;
        } while (node != head);
        return null;
    }
    public void addAfter(T key, T data){
        Node<T> node = new Node<>(data);
        Node<T> temp = findNode(key);
        if(temp == null){
            System.out.println("Node doesn't exist!..");
        }
        else if(temp == tail){
            add(data);
        }
        else {
            node.next = temp.next;
            node.prev = temp;
            temp.next = node;
            node.next.prev = node;
        }
    }

    public T remove(){ // remove last is same as this
        T removed = tail.data;
        if(head == tail){
            head = tail = null;
        }
        else{
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        return removed;
    }
    public T removeFirst(){
        // head  == null edge case through exception
        T removed;
        removed = head.data;
        head = head.next;
        head.prev = tail;
        tail.next = head;
        return removed;
    }
    private Node<T> getNode(int index){
        Node<T> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        return node;
    }
    public T remove(int index){
        T removed;
        if(index == 0)
            return removeFirst();
        Node<T> temp = getNode(index);
        removed = temp.next.data;
        if(temp.next == tail)
            remove();
        else {
            temp.next = temp.next.next;
            temp.next.prev = temp;
        }
        return removed;
    }
    public boolean remove(T key){
        // add edge cases later // i.e, head == null
        if(head.data.equals(key)){
            removeFirst();
            return true;
        }
        if(tail.data.equals(key)){
            remove();
            return true;
        }
        Node<T> temp = findNode(key); // edge case key doesn't exist , temp == null
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        return true;
    }

    public void reverse(){
        Node<T> temp = null;
        Node<T> curr = head;
        do{
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }while (curr != head);

        tail = curr; // curr point to head, change head to tail.
        head = tail.next;
    }
    public void show(){
        Node<T> temp = tail;
        System.out.println("Reverse a Dll");
        do{
            System.out.print(temp.data + " -> ");
            temp = temp.prev;
        }while (temp != tail);
        System.out.print("START\n");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<T> temp = head;
        if(head == null){
            return "[]";
        }
        do {
            builder.append(temp.data).append(", ");
            temp = temp.next;
        }while (temp.next != head);
        builder.append(temp.data).append("]");
        return builder.toString();
    }
}
