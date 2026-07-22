import java.util.Scanner;

public class MergeLinkedListsIntoAVL {

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

        void addAll(SinglyLinkedList anotherList) {
            ListNode current = anotherList.head;

            while (current != null) {
                add(current.data);
                current = current.next;
            }
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

        void insertLinkedList(SinglyLinkedList list) {
            ListNode current = list.head;

            while (current != null) {
                insert(current.data);
                current = current.next;
            }
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

        SinglyLinkedList firstList = new SinglyLinkedList();
        SinglyLinkedList secondList = new SinglyLinkedList();
        SinglyLinkedList mergedList = new SinglyLinkedList();
        AVLTree avlTree = new AVLTree();

        System.out.print("Enter size of first linked list: ");
        int firstSize = scanner.nextInt();

        System.out.println("Enter first linked list elements:");

        for (int i = 0; i < firstSize; i++) {
            firstList.add(scanner.nextInt());
        }

        System.out.print("Enter size of second linked list: ");
        int secondSize = scanner.nextInt();

        System.out.println("Enter second linked list elements:");

        for (int i = 0; i < secondSize; i++) {
            secondList.add(scanner.nextInt());
        }

        mergedList.addAll(firstList);
        mergedList.addAll(secondList);

        avlTree.insertLinkedList(mergedList);

        System.out.println("\nFirst Linked List:");
        firstList.display();

        System.out.println("Second Linked List:");
        secondList.display();

        System.out.println("Merged Linked List:");
        mergedList.display();

        System.out.println("AVL Tree Sorted Unique Elements:");
        avlTree.displayInorder();

        scanner.close();
    }
}
