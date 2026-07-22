import java.util.Scanner;

public class AVLWithInsertionHistory {

    static class HistoryNode {
        int value;
        HistoryNode next;

        HistoryNode(int value) {
            this.value = value;
        }
    }

    static class HistoryList {
        HistoryNode head;
        HistoryNode tail;

        void add(int value) {
            HistoryNode newNode = new HistoryNode(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        void display() {
            if (head == null) {
                System.out.println("Insertion history is empty.");
                return;
            }

            HistoryNode current = head;

            while (current != null) {
                System.out.print(current.value);

                if (current.next != null) {
                    System.out.print(" -> ");
                }

                current = current.next;
            }

            System.out.println();
        }
    }

    static class AVLNode {
        int key;
        int height;
        AVLNode left;
        AVLNode right;

        AVLNode(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static class AVLTree {
        AVLNode root;

        int height(AVLNode node) {
            return node == null ? 0 : node.height;
        }

        int balance(AVLNode node) {
            return node == null
                    ? 0
                    : height(node.left) - height(node.right);
        }

        AVLNode rotateRight(AVLNode y) {
            AVLNode x = y.left;
            AVLNode temporary = x.right;

            x.right = y;
            y.left = temporary;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        AVLNode rotateLeft(AVLNode x) {
            AVLNode y = x.right;
            AVLNode temporary = y.left;

            y.left = x;
            x.right = temporary;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        boolean contains(int key) {
            AVLNode current = root;

            while (current != null) {
                if (key == current.key) {
                    return true;
                }

                current = key < current.key
                        ? current.left
                        : current.right;
            }

            return false;
        }

        boolean insert(int key) {
            if (contains(key)) {
                return false;
            }

            root = insertNode(root, key);
            return true;
        }

        AVLNode insertNode(AVLNode node, int key) {
            if (node == null) {
                return new AVLNode(key);
            }

            if (key < node.key) {
                node.left = insertNode(node.left, key);
            } else {
                node.right = insertNode(node.right, key);
            }

            node.height =
                    Math.max(height(node.left), height(node.right)) + 1;

            int nodeBalance = balance(node);

            if (nodeBalance > 1 && key < node.left.key) {
                return rotateRight(node);
            }

            if (nodeBalance < -1 && key > node.right.key) {
                return rotateLeft(node);
            }

            if (nodeBalance > 1 && key > node.left.key) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            if (nodeBalance < -1 && key < node.right.key) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        void displaySorted() {
            if (root == null) {
                System.out.println("AVL tree is empty.");
                return;
            }

            inorder(root);
            System.out.println();
        }

        void inorder(AVLNode node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.key + " ");
                inorder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AVLTree avlTree = new AVLTree();
        HistoryList historyList = new HistoryList();

        System.out.print("Enter number of operations: ");
        int operations = scanner.nextInt();

        System.out.println("1 value: Insert");
        System.out.println("2: Display sorted AVL tree");
        System.out.println("3: Display insertion history");

        for (int i = 0; i < operations; i++) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int value = scanner.nextInt();

                    if (avlTree.insert(value)) {
                        historyList.add(value);
                        System.out.println(value + " inserted.");
                    } else {
                        System.out.println("Duplicate value ignored.");
                    }
                    break;

                case 2:
                    System.out.println("AVL Tree:");
                    avlTree.displaySorted();
                    break;

                case 3:
                    System.out.println("Insertion History:");
                    historyList.display();
                    break;

                default:
                    System.out.println("Invalid operation.");
            }
        }

        scanner.close();
    }
}
