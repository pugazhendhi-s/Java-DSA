package com.f7_linkedList.l1_singly.custom;

public class LL {
    private static class Node {
        public int val;
        public Node next;

        public Node() {}
        public Node(int val){
            this.val = val;
            next = null;
        }
        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

    }
    private Node head;
    public void add(int val){
        Node node = new Node(val);  // constructor called and node created [val, null]
        if (head == null){
            head = node;
        }
        else{
            Node last = head;
            while (last.next != null){
                last = last.next;
            }
            last.next = node;
        }
    }
    public void addFirst(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;
    }
    public void add(int index, int val){
        Node node = new Node(val);
        if(index == 0){
            addFirst(val);
        }
        else {
            Node curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.next;
            }
            node.next = curNode.next;
            curNode.next = node;
        }
    }
    public int remove(int key){    // 2 3 4 5 6
        Node curNode = head, prev = null;
        if(curNode != null && curNode.val == key) {
            head = curNode.next;
        }
        else {
            while (curNode != null && curNode.val != key) {
                prev = curNode;
                curNode = curNode.next;
            }
            if (curNode != null) {
                prev.next = curNode.next;
            }
        }
        return key;
    }
    public int removeFirst(){
        int removed = head.val;
        head = head.next;
        return removed;
    }
    public int removeLast(){
        int removed;
        if(head != null && head.next == null){
            removed = head.val;
            head = null;
        }
        else {
            Node cur_node = head, prev = null;
            while(cur_node.next != null){
                prev = cur_node;
                cur_node = cur_node.next;
            }
            removed = cur_node.val;
            prev.next = null;
        }
        return removed;
    }
    public int removeAt(int index){
        int removed;
        if(index == 0 && head != null){
            removed = head.val;
            head = head.next;
        }
        else{
            Node curNode = head;
            for (int i = 0; i < index-1; i++) {
                curNode = curNode.next;
            }
            removed = curNode.next.val;
            Node prev = curNode.next; // now prev is removing node, so assign prev.next node to cur.next
            curNode.next = prev.next;
        }
        return removed;
    }
    public boolean contains(int key){
        Node cur_node = head;
        while(cur_node != null){
            if(cur_node.val == key)
                return true;
            cur_node = cur_node.next;
        }
        return false;
    }
    public void reverse(){
        Node current = head;
        Node previous = null;
        Node next;
        while (current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        // current points to null, previous points to last value so make it head
        head = previous;
    }
    public int middleNode(){
        Node slowptr = head;
        Node fastptr = head;
        while (fastptr != null && fastptr.next != null){
            slowptr = slowptr.next;
            fastptr = fastptr.next.next;
        }
        assert slowptr != null;
        return slowptr.val;
    }
    public int size(){
        Node curNode = head;
        int count = 0;
        while (curNode != null){
            count++;
            curNode = curNode.next;
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node temp = head;
        while (temp.next != null){
            builder.append(temp.val).append(", ");
            temp = temp.next;
        }
        builder.append(temp.val).append("]");
        return builder.toString();
    }


}
