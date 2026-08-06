public class TrieWordSearch {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }

    static class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;

            for (char character : word.toCharArray()) {
                int index = character - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }

            current.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode node = findNode(word);
            return node != null && node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            return findNode(prefix) != null;
        }

        private TrieNode findNode(String text) {
            TrieNode current = root;

            for (char character : text.toCharArray()) {
                int index = character - 'a';

                if (current.children[index] == null) {
                    return null;
                }

                current = current.children[index];
            }

            return current;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("heap");
        trie.insert("hello");
        trie.insert("help");
        trie.insert("hero");
        trie.insert("trie");

        System.out.println("Search heap: " + trie.search("heap"));
        System.out.println("Search her: " + trie.search("her"));
        System.out.println("Prefix hel: " + trie.startsWith("hel"));
        System.out.println("Prefix java: " + trie.startsWith("java"));
    }
}
