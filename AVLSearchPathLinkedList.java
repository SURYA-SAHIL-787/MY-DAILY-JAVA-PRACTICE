import java.util.Scanner;

public class AVLSearchPathLinkedList {

    static class PathNode {
        int value;
        PathNode next;

        PathNode(int value) {
            this.value = value;
        }
    }

    static class SearchPathList {
        PathNode head;
        PathNode tail;

        void add(int value) {
            PathNode newNode = new PathNode(value);

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
                System.out.println("No nodes visited.");
                return;
            }

            PathNode current = head;

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

        void insert(int key) {
            root = insert(root, key);
        }

        AVLNode insert(AVLNode node, int key) {
            if (node == null) {
                return new AVLNode(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                return node;
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

        boolean search(int key, SearchPathList path) {
            AVLNode current = root;

            while (current != null) {
                path.add(current.key);

                if (key == current.key) {
                    return true;
                }

                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return false;
        }

        void inorder() {
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

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        System.out.println("Enter " + n + " integers:");

        for (int i = 0; i < n; i++) {
            avlTree.insert(scanner.nextInt());
        }

        System.out.println("\nAVL Tree Inorder:");
        avlTree.inorder();

        System.out.print("Enter value to search: ");
        int searchValue = scanner.nextInt();

        SearchPathList searchPath = new SearchPathList();
        boolean found = avlTree.search(searchValue, searchPath);

        System.out.println("Search Path:");
        searchPath.display();

        if (found) {
            System.out.println(searchValue + " found in the AVL tree.");
        } else {
            System.out.println(searchValue + " not found in the AVL tree.");
        }

        scanner.close();
    }
}
