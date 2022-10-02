package com.myAlgorithms;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    DoubleLinkedList cache;
    Map<Integer, Node> mapToCache;
    int capacity;
    //CONSTRCUT
    public LRU(int cap){
        capacity = cap;
        cache = new DoubleLinkedList();
        mapToCache = new HashMap<>();
    }

    public void printAll() {
        Node curr = cache.head;
        while (curr != null){
            System.out.println(curr.val);
            curr = curr.next;
        }
        System.out.println();
    }

    public int get(int key) {
        if (mapToCache.containsKey(key)) {
            Node temp = cache.remove(mapToCache.get(key));
            cache.addFirst(temp);
            return temp.val;
        } else {
            return  -1;
        }
    }

    public void put(int key, int val) {
        if (mapToCache.containsKey(key)) {
            Node temp = cache.remove(mapToCache.get(key));
            temp.val = val;
            cache.addFirst(temp);
            mapToCache.put(key, temp);
        } else {
            Node temp = new Node(key, val);
            mapToCache.put(key, temp);
            cache.addFirst(temp);
            if (mapToCache.size() > capacity) {
                int keyOfLast = cache.removeLast();
                mapToCache.remove(keyOfLast);
            }
        }
    }

    class DoubleLinkedList {
        Node head;
        Node tail;
        public DoubleLinkedList() {
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
        }
        void addFirst(Node curr) {
            curr.prev = head;
            curr.next = head.next;
            head.next = curr;
            head.next.prev = curr;
        }

        Node remove(Node curr) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            return curr;
        }

        int removeLast() {
            Node last = remove(tail.prev);
            return last.key;
        }
    }
}

class Node {
    Node prev;
    Node next;
    int key;
    int val;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
        this.prev = null;
        this.next = null;
    }
}

