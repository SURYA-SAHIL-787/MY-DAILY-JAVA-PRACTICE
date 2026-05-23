import java.util.*;
import java.util.concurrent.locks.*;

class InvalidCapacityException extends Exception {
    InvalidCapacityException(String message) {
        super(message);
    }
}

class KeyNotFoundException extends Exception {
    KeyNotFoundException(String message) {
        super(message);
    }
}

class ConcurrentLRUCache<K, V> {
    private class Node {
        K key;
        V value;
        Node prev;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node> map;
    private final ReentrantReadWriteLock lock;
    private final Lock readLock;
    private final Lock writeLock;
    private Node head;
    private Node tail;

    public ConcurrentLRUCache(int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }

        this.capacity = capacity;
        this.map = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    public V get(K key) throws KeyNotFoundException {
        writeLock.lock();

        try {
            if (!map.containsKey(key)) {
                throw new KeyNotFoundException("Key not found: " + key);
            }

            Node node = map.get(key);
            moveToFront(node);
            return node.value;
        } finally {
            writeLock.unlock();
        }
    }

    public void put(K key, V value) {
        writeLock.lock();

        try {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                moveToFront(node);
                return;
            }

            Node node = new Node(key, value);

            if (map.size() == capacity) {
                map.remove(tail.key);
                removeNode(tail);
            }

            addToFront(node);
            map.put(key, node);
        } finally {
            writeLock.unlock();
        }
    }

    public void delete(K key) throws KeyNotFoundException {
        writeLock.lock();

        try {
            if (!map.containsKey(key)) {
                throw new KeyNotFoundException("Cannot delete missing key: " + key);
            }

            Node node = map.remove(key);
            removeNode(node);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean contains(K key) {
        readLock.lock();

        try {
            return map.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

    private void addToFront(Node node) {
        node.prev = null;
        node.next = head;

        if (head != null) {
            head.prev = node;
        }

        head = node;

        if (tail == null) {
            tail = node;
        }
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }

    public void printCache() {
        readLock.lock();

        try {
            Node current = head;

            while (current != null) {
                System.out.print("(" + current.key + ":" + current.value + ") ");
                current = current.next;
            }

            System.out.println();
        } finally {
            readLock.unlock();
        }
    }
}

public class ConcurrentLRUCacheDemo {
    public static void main(String[] args) throws Exception {
        ConcurrentLRUCache<Integer, String> cache = new ConcurrentLRUCache<>(3);

        Runnable writer = () -> {
            for (int i = 1; i <= 6; i++) {
                cache.put(i, "Value" + i);
                cache.printCache();
            }
        };

        Runnable reader = () -> {
            for (int i = 1; i <= 6; i++) {
                try {
                    System.out.println("Read: " + cache.get(i));
                } catch (KeyNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(reader);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
