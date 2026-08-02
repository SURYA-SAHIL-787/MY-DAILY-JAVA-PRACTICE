public class WordFrequencyAnalyzer {

    // ---------------- BST FOR WORDS ----------------

    static class WordNode {
        String word;
        int frequency;
        WordNode left;
        WordNode right;

        WordNode(String word) {
            this.word = word;
            this.frequency = 1;
        }
    }

    static class WordBST {
        WordNode root;

        void insert(String word) {
            root = insert(root, word);
        }

        private WordNode insert(WordNode node, String word) {
            if (node == null) {
                return new WordNode(word);
            }

            int comparison = word.compareTo(node.word);

            if (comparison < 0) {
                node.left = insert(node.left, word);
            } else if (comparison > 0) {
                node.right = insert(node.right, word);
            } else {
                node.frequency++;
            }

            return node;
        }

        void displayAlphabetically() {
            inorder(root);
        }

        private void inorder(WordNode node) {
            if (node != null) {
                inorder(node.left);

                System.out.println(
                        node.word + " -> " + node.frequency
                );

                inorder(node.right);
            }
        }
    }

    // ---------------- AVL BY WORD FREQUENCY ----------------

    static class FrequencyNode {
        String word;
        int frequency;
        int height;
        FrequencyNode left;
        FrequencyNode right;

        FrequencyNode(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
            this.height = 1;
        }
    }

    static class FrequencyAVL {
        FrequencyNode root;

        void insert(String word, int frequency) {
            root = insert(root, word, frequency);
        }

        private FrequencyNode insert(
                FrequencyNode node,
                String word,
                int frequency
        ) {
            if (node == null) {
                return new FrequencyNode(word, frequency);
            }

            int comparison = compare(
                    frequency,
                    word,
                    node.frequency,
                    node.word
            );

            if (comparison < 0) {
                node.left = insert(
                        node.left,
                        word,
                        frequency
                );
            } else {
                node.right = insert(
                        node.right,
                        word,
                        frequency
                );
            }

            updateHeight(node);

            int balance = getBalance(node);

            if (balance > 1
                    && compare(
                    frequency,
                    word,
                    node.left.frequency,
                    node.left.word
            ) < 0) {
                return rotateRight(node);
            }

            if (balance < -1
                    && compare(
                    frequency,
                    word,
                    node.right.frequency,
                    node.right.word
            ) > 0) {
                return rotateLeft(node);
            }

            if (balance > 1
                    && compare(
                    frequency,
                    word,
                    node.left.frequency,
                    node.left.word
            ) > 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            if (balance < -1
                    && compare(
                    frequency,
                    word,
                    node.right.frequency,
                    node.right.word
            ) < 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        private int compare(
                int frequency1,
                String word1,
                int frequency2,
                String word2
        ) {
            int result =
                    Integer.compare(frequency1, frequency2);

            if (result != 0) {
                return result;
            }

            return word1.compareTo(word2);
        }

        private int height(FrequencyNode node) {
            return node == null ? 0 : node.height;
        }

        private void updateHeight(FrequencyNode node) {
            node.height =
                    1 + Math.max(height(node.left), height(node.right));
        }

        private int getBalance(FrequencyNode node) {
            return node == null
                    ? 0
                    : height(node.left) - height(node.right);
        }

        private FrequencyNode rotateRight(FrequencyNode y) {
            FrequencyNode x = y.left;
            FrequencyNode middle = x.right;

            x.right = y;
            y.left = middle;

            updateHeight(y);
            updateHeight(x);

            return x;
        }

        private FrequencyNode rotateLeft(FrequencyNode x) {
            FrequencyNode y = x.right;
            FrequencyNode middle = y.left;

            y.left = x;
            x.right = middle;

            updateHeight(x);
            updateHeight(y);

            return y;
        }

        void displayMostFrequentFirst() {
            reverseInorder(root);
        }

        private void reverseInorder(FrequencyNode node) {
            if (node != null) {
                reverseInorder(node.right);

                System.out.println(
                        node.word + " -> " + node.frequency
                );

                reverseInorder(node.left);
            }
        }
    }

    // ---------------- LINKED LIST FOR ORIGINAL ORDER ----------------

    static class TokenNode {
        String word;
        TokenNode next;

        TokenNode(String word) {
            this.word = word;
        }
    }

    static class TokenLinkedList {
        TokenNode head;
        TokenNode tail;

        void add(String word) {
            TokenNode newNode = new TokenNode(word);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        void display() {
            TokenNode current = head;

            while (current != null) {
                System.out.print(current.word);

                if (current.next != null) {
                    System.out.print(" -> ");
                }

                current = current.next;
            }

            System.out.println();
        }
    }

    static void copyBSTToAVL(
            WordNode node,
            FrequencyAVL avl
    ) {
        if (node != null) {
            copyBSTToAVL(node.left, avl);

            avl.insert(
                    node.word,
                    node.frequency
            );

            copyBSTToAVL(node.right, avl);
        }
    }

    public static void main(String[] args) {

        String paragraph =
                "Java trees and linked lists make Java data "
                        + "structures powerful. Trees support searching "
                        + "and arrays support fast access.";

        String cleanedParagraph = paragraph
                .toLowerCase()
                .replaceAll("[^a-z ]", " ");

        String[] words =
                cleanedParagraph.trim().split("\\s+");

        WordBST wordIndex = new WordBST();
        FrequencyAVL frequencyIndex = new FrequencyAVL();
        TokenLinkedList originalOrder =
                new TokenLinkedList();

        for (String word : words) {
            wordIndex.insert(word);
            originalOrder.add(word);
        }

        copyBSTToAVL(
                wordIndex.root,
                frequencyIndex
        );

        System.out.println("WORD ARRAY");

        for (String word : words) {
            System.out.print(word + " ");
        }

        System.out.println(
                "\n\nORIGINAL WORD ORDER (LINKED LIST)"
        );
        originalOrder.display();

        System.out.println(
                "\nALPHABETICAL WORD FREQUENCY (BST)"
        );
        wordIndex.displayAlphabetically();

        System.out.println(
                "\nMOST FREQUENT WORDS FIRST (AVL)"
        );
        frequencyIndex.displayMostFrequentFirst();
    }
}
