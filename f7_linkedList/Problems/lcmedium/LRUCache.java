package com.f7_linkedList.Problems.lcmedium;

import java.util.*;

public class LRUCache {
    private static class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
        Node(){
            this(0, 0);
        }
    }
    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head; //we can't update head as well as tail
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        if(null == n){
            return -1;
        }
        update(n);
        return n.value;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if(null == n){
            n = new Node(key, value);
            map.put(key, n);
            add(n);
        }
        else{
            n.value = value;
            update(n);
        }
        if(map.size() > capacity){
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
        }
    }

    private void update(Node node){
        remove(node);
        add(node);
    }
    private void add(Node node){ // head nxt value must be tail;, we can't update head and tail
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
