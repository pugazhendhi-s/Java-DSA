package com.f7_linkedList.Problems.sll;

import java.util.Stack;

public class LL {

    private static class ListNode{
        int val;
        ListNode next;

        public ListNode() {}
        public ListNode(int val){
            this.val = val;
        }
        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }
    private ListNode head;
    private ListNode tail;
    private int size;

    public LL(){
        this.size = 0;
    }
    public void addFirst(int val){
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
        if (tail == null)
            tail = head;
        size++;
    }
    public void add(int val){
        if(tail == null)
            addFirst(val);
        else {
            ListNode node = new ListNode(val);
            tail.next = node;
            tail = node;
            size++;
        }
    }
    public void add(int index, int val){
        if(index == 0)
            addFirst(val);
        else if(index == size)
            add(val);
        else {
            ListNode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = new ListNode(val, temp.next);
            size++;
        }
    }
    // remove
    public int removeFirst(){
        int removed = head.val;
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return removed;
    }
    public int removeLast(){
        if (size <= 1){
            return removeFirst();
        }
        else{
            // previous node of last node
            ListNode prev = getListNode(size - 2); // second last node
            int removed = tail.val;
            tail = prev;
            tail.next = null;
            size--;
            return removed;
        }
    }
    public int remove(int index){
        if(index == 0)
            return removeFirst();
        if(index == size-1)
            return removeLast();
        ListNode prev = getListNode(index-1);
        int val = prev.next.val;
        prev.next = prev.next.next;
        size--;
        return val;
    }
    public ListNode getListNode(int index){
        ListNode node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public ListNode findListNode(int key){
        ListNode node = head;
        while (node != null){
            if(node.val == key)
                return node;
            node = node.next;
        }
        return null;
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
    // reverse recursion
    public void reverse(){
        ListNode curr = head;
        ListNode prev = null;
        ListNode next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;

        // reverse(head); call reverse by recursion
    }
    private void reverse(ListNode node) {
        if(node == tail){
            head = tail;
            return;
        }
        reverse(node.next);
        tail.next = node;
        tail = node;
        tail.next = null;
    }

    // sub List reverse
    public ListNode reverseII(ListNode node, int left, int right){
        if(left == right)
            return node;
        ListNode prev = null;
        ListNode curr = node;
        for (int i = 0; (curr != null && i < left - 1); i++) {
            prev = curr;
            curr = curr.next;
        }
        // now reverse between left and right
        ListNode prevLeft = prev;
        ListNode subEnd = curr;
        ListNode next;
        for (int i = 0; (curr != null && i < right - left + 1); i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if(prevLeft != null)
            prevLeft.next = prev;
        else
            node = prev;
        subEnd.next = curr; // val at left index , after reversed,
        return node;
    }
    private ListNode middleNode(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // this will return middle and nodes that are all after middle
    }
    private ListNode reverseList(ListNode node){
        ListNode curr = node;
        ListNode prev = null;
        ListNode next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public boolean isPalindrome(ListNode node){
        ListNode mid = middleNode(node);
        ListNode midHead = reverseList(mid);
        ListNode middle = midHead;
        while (node != middle){
            if(node.val != midHead.val)
                break;
            node = node.next;
            midHead = midHead.next;
        }
        reverseList(middle);
        return node == middle;
    }
    // 25. Reverse Nodes in k-Group

    public void reverseKGroup(){
        head = reverseKGroup(head, 3);
    }
    private ListNode reverseKGroup(ListNode node, int k) {
        if(k <= 1 || node == null)
            return node;

        ListNode prev = null;
        ListNode curr = node;
        ListNode next;

        while (true){
            ListNode kLast = curr;
            ListNode prevLeft = prev;
            if(isKNodes(curr, k)) {
                for (int i = 0; i < k; i++) {
                    next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                kLast.next = curr;
                if(prevLeft != null)
                    prevLeft.next = prev;
                else
                    node = prev;  // head = prev
                prev = kLast; // prev for next group
            }
            else
                break;
        }
        return node;
    }
    private boolean isKNodes(ListNode node, int k){
        while (node != null && k > 0) {
            k--;
            node = node.next;
        }
        return k == 0;
    }
    private ListNode reverseKGroups(ListNode node, int k){
        int n = 0;
        // find size of node
        for (ListNode i = node; i != null ; n++, i = i.next);
        ListNode Head = new ListNode(0); // give any val, at end we ignore that
        Head.next = node;
        ListNode prev;
        ListNode tail;
        ListNode next;
        for(prev = Head, tail = node; n >= k; n -= k){
            for (int i = 0; i < k; i++) {
                next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }
            prev = tail;
            tail = tail.next;
        }
        return Head.next;
    }

    public void reverseAlternateKGroups(){
        head = reverseAlternateKGroups(head, 3);
    }
    private ListNode reverseAlternateKGroups(ListNode node, int k) {
        if(k <= 1 || node == null)
            return node;

        ListNode prev = null;
        ListNode curr = node;
        ListNode next;

        while (true){
            ListNode kLast = curr;
            ListNode prevLeft = prev;
            if(isKNodes(curr, k)) {
                for (int i = 0; i < k; i++) {
                    next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                kLast.next = curr; //curr points to after reversing one group, next node in new k group
                if(prevLeft != null)
                    prevLeft.next = prev;
                else
                    node = prev;  // head = prev
            }
            // skip next K groups
            if(isKNodes(curr, k)){
                for (int i = 0; i < k; i++) {
                    prev = curr;
                    curr = curr.next;
                }
            }
            else
                break;
        }
        return node;
    }

    public void rotateRight(int k){
        head = rotateRight(head, k);
    }
    private ListNode rotateRight(ListNode Head, int k) {
        if( k <= 0 ||Head == null || Head.next == null)
            return Head;
        int length = 1;
        ListNode last = Head;
        while (last.next != null){
            last = last.next;
            length++;
        }
        int rotations = k % length;
        if(rotations == 0) {
            return Head;
        }
        last.next = Head;
        int skip = length - rotations;
        ListNode newLast = Head;
        for (int i = 0; i < skip -1; i++) {
            newLast = newLast.next;
        }
        Head = newLast.next;
        newLast.next = null;
        return Head;
    }

    public void reorderList() {
        reorderListI(head);
    }

    private void reorderList(ListNode node) {
        if(node.next == null)
            return;
        ListNode mid = middleNode(node);
        Stack<ListNode> stack = new Stack<>();
        while (mid != null) {
            stack.push(mid);
            mid = mid.next;
        } // instead of stack reverse the value after mid, put in listNode , then merge.
        while (!stack.isEmpty()){
            ListNode temp = node.next;
            node.next = stack.pop();
            node.next.next = temp;
            node = node.next.next;
        }
        node.next = null;
    }
    private void reorderListI(ListNode node){
        ListNode left = middleNode(node);
        ListNode right = left.next;
        left.next = null;
        right = reverseList(right);
        left = head; // 1, 2, 3
        while (left != null && right != null){
            ListNode next = left.next;
            left.next = right;
            left = right;
            right = next;
        }
    }

    public void removeNthFromEnd(int n){
        head = removeNthFromEndI(head, n);
    }
    private ListNode removeNthFromEnd(ListNode node, int n) {
        if(node == null)
            return null;
        int len = 0;
        for(ListNode i=node; i != null; i = i.next, len++);
        int index = len-n;
        if(index == 0) {
            return node.next;
        }
        ListNode newHead = node;
        for(int i=0; i<index-1; i++){
            node = node.next;
        }
        ListNode prev = node.next;
        if(prev != null)
            node.next = prev.next;
        return newHead;
    }
    private ListNode removeNthFromEndI(ListNode node, int n){
        ListNode fast = node, slow = node;
        while (n-- > 0) fast = fast.next;
        if(fast == null)
            return node.next;  // node can't be null, constraint node >= 1
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; // slow , prev index of node to be deleted
        return node;
    }

    public void swapNodes(int k) {
        head = swapNodes(head, k);
    }
    private ListNode swapNodes(ListNode node, int k) {
        ListNode fast = node, slow = node;
        ListNode first,second;
        
        // move fast to position of k-1
        for (int i = 0; i < k-1; i++)
            fast = fast.next;

        first = fast;          // save this node for swap

        // now move to slow to position of length-k , fast reached null(length), k steps fast already moved
        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        second = slow;

        // swap first and second
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        return node;
    }
    public void addTwoNumbers(LL l1, LL l2){
        head = addTwoNumbersIIStack(l1.head, l2.head);
    }
    private ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode add = new ListNode(0);
        ListNode list = add;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){

            int a = (l1 != null) ? l1.val : 0;
            int b = (l2 != null) ? l2.val : 0;
            int sum = a + b + carry;
            carry = sum / 10;

            add.next = new ListNode(sum % 10);
            add = add.next;
            l1 = (l1 != null) ? l1.next : l1;
            l2 = (l2 != null) ? l2.next : l2;
        }
        return list.next;
    }
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode add = new ListNode(0);
        ListNode list = add;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){

            int a = (l1 != null) ? l1.val : 0;
            int b = (l2 != null) ? l2.val : 0;
            int sum = a + b + carry;
            carry = sum / 10;

            add.next = new ListNode(sum % 10);
            add = add.next;
            l1 = (l1 != null) ? l1.next : l1;
            l2 = (l2 != null) ? l2.next : l2;
        }
        list = reverseList(list.next);
        return list;
    }
    public ListNode addTwoNumbersIIStack(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null || l2 != null){
            if(l1 != null){
                s1.push(l1.val);
                l1 = l1.next;
            }
            if(l2 != null){
                s2.push(l2.val);
                l2 = l2.next;
            }
        }
        int carry = 0;
        Stack<ListNode> add = new Stack<>();
        while(!s1.isEmpty() || !s2.isEmpty() || carry != 0){

            int a = (s1.isEmpty()) ? 0 : s1.pop();
            int b = (s2.isEmpty()) ? 0 : s2.pop();
            int sum = a + b + carry;
            carry = sum / 10;

            add.push(new ListNode(sum % 10));
        }
        ListNode node = new ListNode();
        ListNode list = node;
        while (!add.isEmpty()){
            node.next = add.pop();
            node = node.next;
        }
        return list.next;
    }











}
