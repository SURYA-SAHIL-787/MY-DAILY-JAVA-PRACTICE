import java.util.*;

public class TrieGraphSearch {
    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end;
    }

    static TrieNode root = new TrieNode();

    static void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int i = ch - 'a';
            if (node.child[i] == null) node.child[i] = new TrieNode();
            node = node.child[i];
        }
        node.end = true;
    }

    static boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int i = ch - 'a';
            if (node.child[i] == null) return false;
            node = node.child[i];
        }
        return node.end;
    }

    public static void main(String[] args) {
        String[] cities = {"delhi", "mumbai", "chennai", "goa"};

        for (String city : cities) insert(city);

        System.out.println(search("goa"));
        System.out.println(search("pune"));
    }
}
