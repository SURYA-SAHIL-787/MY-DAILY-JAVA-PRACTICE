import java.util.*;
import java.util.concurrent.locks.*;

class InvalidWordException extends Exception {
    InvalidWordException(String message) {
        super(message);
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
}

class ConcurrentTrie {
    private final TrieNode root;
    private final ReentrantReadWriteLock lock;

    public ConcurrentTrie() {
        this.root = new TrieNode();
        this.lock = new ReentrantReadWriteLock();
    }

    public void insert(String word) throws InvalidWordException {
        validate(word);

        lock.writeLock().lock();

        try {
            TrieNode current = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }

            current.isEnd = true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean search(String word) throws InvalidWordException {
        validate(word);

        lock.readLock().lock();

        try {
            TrieNode node = findNode(word);
            return node != null && node.isEnd;
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<String> autocomplete(String prefix) throws InvalidWordException {
        validate(prefix);

        lock.readLock().lock();

        try {
            TrieNode node = findNode(prefix);
            List<String> result = new ArrayList<>();

            if (node == null) {
                return result;
            }

            dfs(node, new StringBuilder(prefix), result);
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }

    private TrieNode findNode(String text) {
        TrieNode current = root;

        for (char ch : text.toCharArray()) {
            int index = ch - 'a';

            if (current.children[index] == null) {
                return null;
            }

            current = current.children[index];
        }

        return current;
    }

    private void dfs(TrieNode node, StringBuilder path, List<String> result) {
        if (node.isEnd) {
            result.add(path.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                path.append((char) ('a' + i));
                dfs(node.children[i], path, result);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    private void validate(String word) throws InvalidWordException {
        if (word == null || word.isEmpty()) {
            throw new InvalidWordException("Word cannot be null or empty");
        }

        for (char ch : word.toCharArray()) {
            if (ch < 'a' || ch > 'z') {
                throw new InvalidWordException("Only lowercase a-z allowed");
            }
        }
    }
}

public class ConcurrentTrieSearchEngine {
    public static void main(String[] args) throws Exception {
        ConcurrentTrie trie = new ConcurrentTrie();

        Runnable insertJob = () -> {
            String[] words = {"apple", "app", "apply", "apt", "bat", "batch", "battle"};

            for (String word : words) {
                try {
                    trie.insert(word);
                } catch (InvalidWordException e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        Thread t1 = new Thread(insertJob);
        Thread t2 = new Thread(insertJob);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(trie.search("apple"));
        System.out.println(trie.autocomplete("ap"));
        System.out.println(trie.autocomplete("bat"));
    }
}
