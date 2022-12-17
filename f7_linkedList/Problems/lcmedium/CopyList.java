package com.f7_linkedList.Problems.lcmedium;

public class CopyList {
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    public Node copyRandomList(Node head) {
        if(head == null)
            return null;
        Node cur = head;
        while (cur != null){
            Node nxt = cur.next;
            cur.next = new Node(cur.val, nxt, null);
            cur = nxt;
        }
        cur = head;
        while (cur != null){
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node copyHead = head.next;
        while (cur != null){
            Node next = cur.next.next;
            Node copy = cur.next;
            if(next != null)
                copy.next = next.next;
            cur = next;
        }
        return copyHead;
    }
}
