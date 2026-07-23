public class DeleteAVLValuesFromList {

    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
        }
    }

    static class AVLNode {
        int data;
        int height;
        AVLNode left;
        AVLNode right;

        AVLNode(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    static int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    static int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static AVLNode insert(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        if (balance > 1 && data < node.left.data) {
            return rotateRight(node);
        }

        if (balance < -1 && data > node.right.data) {
            return rotateLeft(node);
        }

        if (balance > 1 && data > node.left.data) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && data < node.right.data) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    static AVLNode minimumNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    static AVLNode delete(AVLNode root, int data) {
        if (root == null) {
            return null;
        }

        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
            if (root.left == null || root.right == null) {
                AVLNode child = root.left != null ? root.left : root.right;

                if (child == null) {
                    root = null;
                } else {
                    root = child;
                }
            } else {
                AVLNode successor = minimumNode(root.right);
                root.data = successor.data;
                root.right = delete(root.right, successor.data);
            }
        }

        if (root == null) {
            return null;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rotateRight(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateLeft(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    static ListNode add(ListNode head, int data) {
        ListNode newNode = new ListNode(data);

        if (head == null) {
            return newNode;
        }

        ListNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return head;
    }

    static AVLNode deleteListValues(AVLNode root, ListNode head) {
        ListNode current = head;

        while (current != null) {
            root = delete(root, current.data);
            current = current.next;
        }

        return root;
    }

    static void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        AVLNode root = null;

        int[] values = {40, 20, 60, 10, 30, 50, 70};

        for (int value : values) {
            root = insert(root, value);
        }

        ListNode deleteList = null;
        deleteList = add(deleteList, 20);
        deleteList = add(deleteList, 60);

        System.out.print("Before deletion: ");
        inorder(root);

        root = deleteListValues(root, deleteList);

        System.out.print("\nAfter deletion: ");
        inorder(root);
    }
}
