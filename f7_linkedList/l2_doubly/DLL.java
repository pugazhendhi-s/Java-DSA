package com.f7_linkedList.l2_doubly;

public class DLL<T> {
    //https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/submissions/
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

    public void add(T data){ // addLast also same
        if (head == null)
            addFirst(data);
        else{
            Node<T> node = new Node<>(data);
            Node<T> last = head;
            while (last.next != null){
                last = last.next;
            }
            node.next = null;
            node.prev = last;
            last.next = node;
        }
    }
    public void addFirst(T data){
        Node<T> node = new Node<>(data);
        node.next = head;
        node.prev = null;
        if(head != null)
            head.prev = node;
        head = node;
    }
    public void add(int index, T data){
        if(index == 0)
            addFirst(data);
        else {
            Node<T> node = new Node<>(data);
            Node<T> temp = getNode(index);
            if (temp == null)
                add(data);
            else {
                node.next = temp.next;
                node.prev = temp;
                temp.next = node;
                if (node.next != null) // when you are adding node at last index
                    node.next.prev = node;
            }
        }
    }
    public void addAfter(T key, T data){
        Node<T> node = new Node<>(data);
        Node<T> temp = findNode(key);
        if(temp == null){
            System.out.println("Node doesn't exist!..");
        }
        else {
            node.next = temp.next;
            node.prev = temp;
            temp.next = node;
            if (node.next != null) // when you are adding node at last index
                node.next.prev = node;
        }
    }

    public T remove(){ // remove last is same as this
        Node<T> last = head;
        while (last.next != null){
            last = last.next;
        }
        T removed = last.data;
        last.prev.next = null;
        return removed;
    }
    public T removeFirst(){
        // head  == null edge case through exception
        T removed;
        removed = head.data;
        head = head.next;
        head.prev = null;
        return removed;
    }
    public T remove(int index){
        T removed;
        if(index == 0)
            return removeFirst();

        Node<T> temp = getNode(index);
        removed = temp.next.data;
        if(temp.next.next == null)
            temp.next = null;
        else {
            temp.next = temp.next.next;
            temp.next.prev = temp;
        }
        return removed;
    }
    public boolean remove(T key){

        Node<T> temp = findNode(key);
        if(temp == null)  // edge case, key doesn't exist in node
            return false;
        if(temp.prev == null) {
            removeFirst();
        }
        else if(temp.next == null) {
            remove(); // remove last
        }
        else {
            temp.prev.next = temp.next;
            temp.next.prev = temp;
        }
        return true;
    }
    public void reverse(){
        Node<T> temp = null;
        Node<T> current = head;
        while (current != null){
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if(temp != null)
            head = temp.prev;
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
    private Node<T> getNode(int index){
        Node<T> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        return node;
    }

    public void show(){
        Node<T> temp = head;
        Node<T> last = head;
        while (temp != null){
            System.out.print(temp.data + " -> ");
            last = temp;
            temp = temp.next;
        }
        System.out.print("END\n");
        System.out.println("Reverse a Dll");
        while (last != null){
            System.out.print(last.data + " -> ");
            last = last.prev;
        }
        System.out.print("START\n");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<T> temp = head;
        if(head == null){
            return "[]";
        }
        while (temp.next != null){
            builder.append(temp.data).append(", ");
            temp = temp.next;
        }
        builder.append(temp.data).append("]");
        return builder.toString();
    }
}
