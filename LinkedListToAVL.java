import java.util.Scanner;

public class LinkedListToAVL {

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

        void display() {
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

        int getBalance(AVLNode node) {
            return node == null
                    ? 0
                    : height(node.left) - height(node.right);
        }

        AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode temporary = x.right;

            x.right = y;
            y.left = temporary;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        AVLNode leftRotate(AVLNode x) {
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

            int balance = getBalance(node);

            if (balance > 1 && key < node.left.key) {
                return rightRotate(node);
            }

            if (balance < -1 && key > node.right.key) {
                return leftRotate(node);
            }

            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
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

        SinglyLinkedList linkedList = new SinglyLinkedList();
        AVLTree avlTree = new AVLTree();

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        System.out.println("Enter " + n + " integers:");

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();

            linkedList.add(value);
            avlTree.insert(value);
        }

        System.out.println("\nLinked List:");
        linkedList.display();

        System.out.println("AVL Tree Inorder Traversal:");
        avlTree.inorder();

        scanner.close();
    }
}
