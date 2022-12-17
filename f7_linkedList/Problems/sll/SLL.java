package com.f7_linkedList.Problems.sll;

public class SLL {
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
    public void insertRec(int index, int val){
        head = insertRec(index, val, head);
    }
    private Node insertRec(int index, int val, Node node){
        if(index == 0){
            Node temp = new Node(val, node); // inserting new node
            return temp;
        }
        node.next = insertRec(index-1, val, node.next);
        return node;
    }
    public void deleteDuplicates(){
        head = deleteDuplicatesRec(head);
    }
    public Node deleteDuplicatesRec(Node node){
        if(node == null || node.next == null)
            return node;
        node.next = deleteDuplicatesRec(node.next);
        return node.val == node.next.val ? node.next : node;
    }
    public Node deleteDuplicates(Node node) {
        Node head = node;
        if(node == null)
            return null;
        while(node.next != null){
            if(node.val == node.next.val)
                node.next = node.next.next;
            else
                node = node.next;
        }
        return head;
    }
    public static SLL mergeLists(SLL l1, SLL l2) {
        Node n1 = l1.head;
        Node n2 = l2.head;
        SLL list = new SLL();
        while (n1 != null && n2 != null){
            if(n1.val <= n2.val){
                list.add(n1.val);
                n1 = n1.next;
            }
            else{
                list.add(n2.val);
                n2 = n2.next;
            }
        }
        while (n1 != null){
            list.add(n1.val);
            n1 = n1.next;
        }
        while (n2 != null){
            list.add(n2.val);
            n2 = n2.next;
        }
        return list;
    }
    public Node merge(Node n1, Node n2){
        Node head = new Node(); // assigning empty node, val = 0;
        Node tail = head;
        while (n1 != null && n2 != null){
            if(n1.val <= n2.val){
                tail.next = n1;
                tail = tail.next;
                n1 = n1.next;
            }
            else{
                tail.next = n2;
                tail = tail.next;
                n2 = n2.next;
            }
        }
        tail.next = n1 != null ? n1 : n2;
        return head.next; // at first, we're assigning one node head, that is not needed
    }

}
