package com.f7_linkedList.Problems.lceasy;

import java.util.*;

public class LCEasy {
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
    public void add(int val){
        ListNode node = new ListNode(val);
        if(tail == null){
            head = node;
        }
        else {
            tail.next = node;
        }
        tail = node;
    }
    @Override
    public String toString() {
        if(head == null)
            return null;
        StringBuilder builder = new StringBuilder("[");
        ListNode temp = head;
        while (temp.next != null){
            builder.append(temp.val).append(", ");
            temp = temp.next;
        }
        builder.append(temp.val).append("]");
        return builder.toString();
    }
    public int getDecimalValue(){
        return getDecimalValue(head);
    }
    private int getDecimalValue(ListNode node) {
        int num = node.val;
        while(node.next != null){
            //num = num*2 + node.next.val; // num value will raise after the leading zeros
            num = (num << 1) | node.next.val;
            node = node.next;
            // | OR operator because if (num << 1) for all leading zeros,
            // whenever node is 1 after leading zeros, OR execute at this time only

        }
        return num;
    }

    public void deleteDuplicatesII(){
        head = deleteDuplicatesII(head);
    }
    private ListNode deleteDuplicatesII(ListNode node) {
        ListNode list = new ListNode(-101, node);
        ListNode prev = list;

        while(node != null){
            while(node.next != null && node.val == node.next.val){
                node = node.next; // this will end at last node of duplicates
            }
            if(prev.next != node){  // if prev != node.next, so there may be duplicates pointed by while (curr node)
                prev.next = node.next;
            }
            else{
                prev = prev.next;
            }
            node = node.next;
        }

        return list.next;
    }

    public void partition(int pivotIndex){
        head = partition(head, pivotIndex);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode l1 = left;
        ListNode right = new ListNode(0);
        ListNode r1 = right;
        while (head != null){
            if(head.val >= x){
                r1.next = head;
                r1 = r1.next;
            }
            else{
                l1.next = head;
                l1 = l1.next;
            }
            head = head.next;
        }
        r1.next = null;  // right.next point to head values. make sure to make null;
        l1.next = right.next;
        return left.next;
    }
    public void swapPairs(){
        head = swapPairs(head);
    }
    public void reverse(){
        head = reverse(head);
    }
    public ListNode reverse(ListNode node){
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
    private ListNode swapPairs(ListNode node) {
        ListNode dummy = new ListNode(0);
        dummy.next = node;
        ListNode slow = node, fast = node.next, prev = dummy;
        while (fast != null){ // node.next != null
            slow.next = fast.next;
            fast.next = slow;
            prev.next = fast;
            prev = slow;
            slow = slow.next;
            if(slow.next == null)
                break;
            fast = slow.next;
        }
        return dummy.next;
    }
    public void removeZeroSumSubLists(){
        head = removeZeroSumSublists(head);
    }
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> seen = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int prefix = 0;
        seen.put(prefix, dummy); // initially sums start at 0;

        for (ListNode i = head; i != null ; i = i.next) {
            prefix += i.val;
            seen.put(prefix, i);
        }
        prefix = 0;
        for (ListNode i = dummy; i != null ; i = i.next) {
            prefix += i.val;
            i.next = seen.get(prefix).next;
        }
        return dummy.next;
    }

    public void reverseEvenLengthGroups() {
        //head = reverseEvenLengthGroups(head);
        head = reverseEvenLengthGroups(head, 1);
    }
    private ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = head;
        ListNode prev = dummy;
        Stack<ListNode> stack = new Stack<>();
        int group = 1;
        while (curr != null){
            int count = 0;
            ListNode temp = curr;
            while (count != group && curr != null){
                stack.push(curr);
                curr = curr.next;
                count++;
            }
            if(count % 2 == 0){
                while (!stack.isEmpty()){
                    prev.next = stack.pop();
                    prev = prev.next;
                }
            }
            else{
                prev.next = temp;
                while(count-- > 0) {
                    stack.pop();
                    prev = prev.next;
                }
            }
            group++;
        }
        prev.next = null;
        return dummy.next;
    }
    private ListNode reverseEvenLengthGroups(ListNode head, int groupRequired) {
        if(head == null)
            return null;
        ListNode curr = head, prev = null;
        int count = 0;
        while (count < groupRequired && curr != null){
            prev = curr;
            curr = curr.next;
            count++;
        }
        boolean reverse = count % 2 == 0;
        if (!reverse && curr != null)
            // if not reverse, next recursion  values attached to end of prev group
            prev.next = reverseEvenLengthGroups(curr, groupRequired+1);
        if(!reverse)
            return head;
        prev = reverseGroup(head, groupRequired);
        // if reversed, next recursion  values attached to head of prev group.
        head.next = reverseEvenLengthGroups(curr, groupRequired+1);
        return prev;
    }
    private ListNode reverseGroup(ListNode curr, int groupRequired){
        ListNode prev = null;
        ListNode next;
        while (curr != null && groupRequired-- > 0){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public void mergeInBetween(){
        LCEasy ll1 = new LCEasy();
        ll1.add(0);
        ll1.add(1);
        ll1.add(2);
        ll1.add(3);
        ll1.add(4);
        ll1.add(5);
        ll1.add(6);
        LCEasy ll2 = new LCEasy();
        ll2.add(1000000);
        ll2.add(1000001);
        ll2.add(1000002);
        head = mergeInBetween(ll1.head, 3, 4, ll2.head);
    }
    private ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode end = list1, start = null;
        for (int i = 0; i < b; i++, end = end.next) {
            start = (i == a-1) ? end : start;
        }
        start.next = list2;
        while (list2.next != null)
            list2 = list2.next;
        list2.next = end;
        end.next = null;
        return list1;
    }

    public void deleteMiddle(){
        head = deleteMiddle(head);
    }
    private ListNode deleteMiddle(ListNode head) {
        if(head.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow points to before middle node
        slow.next = slow.next.next;
        return head;
    }

    public void oddEvenList(){
        head = oddEvenList(head);
    }
    private ListNode oddEvenList(ListNode head) {
        if(head == null)
            return null;
        ListNode odd = head;
        ListNode even = head.next, evenHead = even;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public int getRandom(){  // reservoir sampling
        ListNode node = head;
        int i = 1; int ans = 0;
        while (node != null){
            double rand = Math.random() * i;
            if(rand < 1)
                ans = node.val;
            node = node.next;
            i++;
        }
        return ans;
    }

    public void splitListToParts() {
        ListNode[] nodes = splitListToParts(head, 3);
        System.out.print("[ ");
        for (ListNode node : nodes) {
            if(node == null){
                System.out.print("[], ");
                continue;
            }
            System.out.print("[");
            while (node.next != null) {
                System.out.print(node.val + ", ");
            }
            System.out.print(node.val + "], ");
        }
    }
    private ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        for(ListNode i=head; i != null; i = i.next) n++;
        ListNode[] nodes = new ListNode[k];
        int size = n / k, extra = n % k;

        ListNode curr = head;
        for(int i = 0; i < k && curr != null; i++, extra--){
            nodes[i] = curr;
            for (int j = 0; j < size + (extra > 0 ? 1 : 0); j++)
                curr = curr.next;
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return nodes;
    }

    public void nodeBetweenCriticalPoints(){
        int[] criticalPoints = nodesBetweenCriticalPoints(head);
        System.out.println(Arrays.toString(criticalPoints));
    }
    private int[] nodesBetweenCriticalPoints(ListNode head) {
        int minima = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = 0;

        ListNode prev = head;
        ListNode curr = head.next;
        ListNode after = head.next.next;
        int i = 1;
        while(after != null){
            if( (curr.val > prev.val && curr.val > after.val) ||
                    (curr.val < prev.val && curr.val < after.val) ){
                if(last != 0){
                    minima = Math.min(minima, i-last);
                }
                first = Math.min(first, i);
                last = i;
            }
            i++;
            prev = curr;
            curr = after;
            after = after.next;
        }
        if(minima == Integer.MAX_VALUE)
            return new int[]{-1, -1};
        return new int[]{minima, last-first};
    }
}
