package hashing.easy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class MyHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 769 is a prime number chosen as the bucket size to reduce hash collisions
    // and distribute keys more uniformly.
    private static final int SIZE = 769;
    private List<Node<K, V>>[] buckets;

    public MyHashMap() {
        buckets = new List[SIZE];
        for (int i = 0; i < SIZE; i ++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // Calculate the hash value for a given key
    private int hash(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int idx = hash(key);
        for (Node<K, V> node : buckets[idx]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        buckets[idx].add(new Node<>(key, value));
    }

    public V get(K key) {
        int idx = hash(key);
        for (Node<K, V> node : buckets[idx]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int idx = hash(key);
        Iterator<Node<K, V>> it = buckets[idx].iterator();
        while (it.hasNext()) {
            if (it.next().key.equals(key)) {
                it.remove();
                return;
            }
        }
    }

    public void clear() {
        for (int i = 0; i < SIZE; i++) {
            buckets[i].clear();
        }
    }
}
public class DesignHashMap {
    public static void main(String[] args) {
        MyHashMap<Integer, Integer> mp = new MyHashMap<>();

        mp.put(10, 10);
        System.out.println(mp.get(10));
        mp.put(10, 20);
        System.out.println(mp.get(10));
    }
}
