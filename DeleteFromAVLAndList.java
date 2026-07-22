import java.util.Scanner;

public class DeleteFromAVLAndList {

    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
        }
    }

    static class SinglyLinkedList {
        ListNode head;
        ListNode tail;

        void add(int value) {
            ListNode newNode = new ListNode(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        boolean delete(int value) {
            if (head == null) {
                return false;
            }

            if (head.data == value) {
                head = head.next;

                if (head == null) {
                    tail = null;
                }

                return true;
            }

            ListNode current = head;

            while (current.next != null) {
                if (current.next.data == value) {
                    if (current.next == tail) {
                        tail = current;
                    }

                    current.next = current.next.next;
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        void display() {
            if (head == null) {
                System.out.println("Empty");
                return;
            }

            ListNode current = head;

            while (current != null) {
                System.out.print(current.data);

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

            updateHeight(node);
            return rebalanceAfterInsertion(node, key);
        }

        void delete(int key) {
            root = delete(root, key);
        }

        AVLNode delete(AVLNode node, int key) {
            if (node == null) {
                return null;
            }

            if (key < node.key) {
                node.left = delete(node.left, key);
            } else if (key > node.key) {
                node.right = delete(node.right, key);
            } else {
                if (node.left == null || node.right == null) {
                    node = node.left != null ? node.left : node.right;
                } else {
                    AVLNode successor = minimumNode(node.right);
                    node.key = successor.key;
                    node.right = delete(node.right, successor.key);
                }
            }

            if (node == null) {
                return null;
            }

            updateHeight(node);

            int nodeBalance = balance(node);

            if (nodeBalance > 1 && balance(node.left) >= 0) {
                return rotateRight(node);
            }

            if (nodeBalance > 1 && balance(node.left) < 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            if (nodeBalance < -1 && balance(node.right) <= 0) {
                return rotateLeft(node);
            }

            if (nodeBalance < -1 && balance(node.right) > 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        void updateHeight(AVLNode node) {
            node.height =
                    Math.max(height(node.left), height(node.right)) + 1;
        }

        AVLNode rebalanceAfterInsertion(AVLNode node, int key) {
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

        AVLNode minimumNode(AVLNode node) {
            AVLNode current = node;

            while (current.left != null) {
                current = current.left;
            }

            return current;
        }

        void displayInorder() {
            if (root == null) {
                System.out.println("Empty");
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

        SinglyLinkedList linkedList = new SinglyLinkedList();
        AVLTree avlTree = new AVLTree();

        System.out.print("Enter number of unique elements: ");
        int n = scanner.nextInt();

        System.out.println("Enter " + n + " unique integers:");

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();

            linkedList.add(value);
            avlTree.insert(value);
        }

        System.out.print("Enter number of values to delete: ");
        int deletionCount = scanner.nextInt();

        System.out.println("Enter values to delete:");

        for (int i = 0; i < deletionCount; i++) {
            int value = scanner.nextInt();

            if (linkedList.delete(value)) {
                avlTree.delete(value);
                System.out.println(value + " deleted.");
            } else {
                System.out.println(value + " not found.");
            }
        }

        System.out.println("\nFinal Linked List:");
        linkedList.display();

        System.out.println("Final AVL Tree Inorder:");
        avlTree.displayInorder();

        scanner.close();
    }
}
