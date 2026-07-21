import java.util.Scanner;

public class BTreeStringDictionary {

    static class BTreeNode {
        String[] words;
        BTreeNode[] children;
        int wordCount;
        int minimumDegree;
        boolean isLeaf;

        BTreeNode(int minimumDegree, boolean isLeaf) {
            this.minimumDegree = minimumDegree;
            this.isLeaf = isLeaf;
            this.words = new String[2 * minimumDegree - 1];
            this.children = new BTreeNode[2 * minimumDegree];
        }

        boolean search(String target) {
            int index = 0;

            while (index < wordCount
                    && target.compareTo(words[index]) > 0) {
                index++;
            }

            if (index < wordCount
                    && target.equals(words[index])) {
                return true;
            }

            if (isLeaf) {
                return false;
            }

            return children[index].search(target);
        }

        void insertNonFull(String word) {
            int index = wordCount - 1;

            if (isLeaf) {
                while (index >= 0
                        && word.compareTo(words[index]) < 0) {
                    words[index + 1] = words[index];
                    index--;
                }

                words[index + 1] = word;
                wordCount++;
            } else {
                while (index >= 0
                        && word.compareTo(words[index]) < 0) {
                    index--;
                }

                index++;

                if (children[index].wordCount
                        == 2 * minimumDegree - 1) {
                    splitChild(index, children[index]);

                    if (word.compareTo(words[index]) > 0) {
                        index++;
                    }
                }

                children[index].insertNonFull(word);
            }
        }

        void splitChild(int index, BTreeNode fullChild) {
            BTreeNode newChild =
                    new BTreeNode(minimumDegree, fullChild.isLeaf);

            newChild.wordCount = minimumDegree - 1;

            for (int i = 0; i < minimumDegree - 1; i++) {
                newChild.words[i] =
                        fullChild.words[i + minimumDegree];
            }

            if (!fullChild.isLeaf) {
                for (int i = 0; i < minimumDegree; i++) {
                    newChild.children[i] =
                            fullChild.children[i + minimumDegree];
                }
            }

            fullChild.wordCount = minimumDegree - 1;

            for (int i = wordCount; i >= index + 1; i--) {
                children[i + 1] = children[i];
            }

            children[index + 1] = newChild;

            for (int i = wordCount - 1; i >= index; i--) {
                words[i + 1] = words[i];
            }

            words[index] = fullChild.words[minimumDegree - 1];
            wordCount++;
        }
    }

    static class BTree {
        BTreeNode root;
        int minimumDegree;

        BTree(int minimumDegree) {
            this.minimumDegree = minimumDegree;
        }

        void insert(String word) {
            if (contains(word)) {
                return;
            }

            if (root == null) {
                root = new BTreeNode(minimumDegree, true);
                root.words[0] = word;
                root.wordCount = 1;
                return;
            }

            if (root.wordCount == 2 * minimumDegree - 1) {
                BTreeNode newRoot =
                        new BTreeNode(minimumDegree, false);

                newRoot.children[0] = root;
                newRoot.splitChild(0, root);

                int index = 0;

                if (word.compareTo(newRoot.words[0]) > 0) {
                    index++;
                }

                newRoot.children[index].insertNonFull(word);
                root = newRoot;
            } else {
                root.insertNonFull(word);
            }
        }

        boolean contains(String word) {
            return root != null && root.search(word);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int minimumDegree = scanner.nextInt();
        int numberOfWords = scanner.nextInt();

        BTree dictionary = new BTree(minimumDegree);

        for (int i = 0; i < numberOfWords; i++) {
            dictionary.insert(scanner.next().toLowerCase());
        }

        int numberOfQueries = scanner.nextInt();

        for (int i = 0; i < numberOfQueries; i++) {
            String query = scanner.next().toLowerCase();

            if (dictionary.contains(query)) {
                System.out.println(query + " FOUND");
            } else {
                System.out.println(query + " NOT FOUND");
            }
        }

        scanner.close();
    }
}
