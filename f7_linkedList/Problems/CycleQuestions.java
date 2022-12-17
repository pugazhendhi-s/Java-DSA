package com.f7_linkedList.Problems;



public class CycleQuestions {
    private static class ListNode{
        int val;
        ListNode next;
        
        public ListNode(){}
        public ListNode(int val) {
            this.val = val;
        }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 141. Linked List Cycle
    //
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                return true;
        }
        return false;
    }

    // find the length of the cycle
    public int lengthCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                int length = 0;
                do{
                    slow = slow.next;
                    length++;
                } while (slow != fast);
                return length;
            }
        }
        return 0;
    }
    // explicitly find length of cycle by other method and call in that.
    public ListNode detectCycles(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        int length = 0;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                length = lengthCycle(slow);
                break;
            }
        }
        ListNode second = head;
        ListNode first = head;
        if(length == 0)
            return null;
        while (length != 0){
            second = second.next;
            length--;
        }
        while (first != second){
            first = first.next;
            second = second.next;
        }
        return first;
    }

    // direct implementation, if slow == fast, move slow to head, now move fast and slow one by one step, both will meet at cycle first node
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                slow = head; // reset slow to head
                while(slow != fast){
                    slow = slow.next; // moving slow by 1
                    fast = fast.next; // moving fast by 1 as well
                }
                return slow; // Or return fast same thing, they will return the tail where cycle starts
            }
        }
        return null;
    }

    public static boolean isHappyNumber(int n){
        int slow = n;
        int fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (fast != 1 && fast != slow);
        return fast == 1;
    }
    private static int getNext(int n){ // 12
        int sum = 0;
        while (n > 0){
            int r = n % 10;
            sum += (r * r);
            n /= 10;
        }
        return sum;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // this will return middle and nodes that are all after middle
    }



}
