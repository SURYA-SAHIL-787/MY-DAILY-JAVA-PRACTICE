import java.util.Scanner;

public class RedBlackTreeColoredTraversal {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    static class Node {
        int data;
        boolean color = RED;
        Node left;
        Node right;
        Node parent;

        Node(int data) {
            this.data = data;
        }
    }

    static class RedBlackTree {
        private Node root;

        void insert(int data) {
            Node newNode = new Node(data);
            Node parent = null;
            Node current = root;

            while (current != null) {
                parent = current;

                if (data < current.data) {
                    current = current.left;
                } else if (data > current.data) {
                    current = current.right;
                } else {
                    return;
                }
            }

            newNode.parent = parent;

            if (parent == null) {
                root = newNode;
            } else if (data < parent.data) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }

            fixInsertion(newNode);
        }

        void printInorder() {
            inorder(root);
            System.out.println();
        }

        void printPreorder() {
            preorder(root);
            System.out.println();
        }

        private void inorder(Node node) {
            if (node == null) {
                return;
            }

            inorder(node.left);
            printNode(node);
            inorder(node.right);
        }

        private void preorder(Node node) {
            if (node == null) {
                return;
            }

            printNode(node);
            preorder(node.left);
            preorder(node.right);
        }

        private void printNode(Node node) {
            String color = node.color == RED ? "R" : "B";
            System.out.print(node.data + "(" + color + ") ");
        }

        private void fixInsertion(Node node) {
            while (node != root
                    && getColor(getParent(node)) == RED) {

                Node parent = getParent(node);
                Node grandparent = getParent(parent);

                if (parent == getLeft(grandparent)) {
                    Node uncle = getRight(grandparent);

                    if (getColor(uncle) == RED) {
                        setColor(parent, BLACK);
                        setColor(uncle, BLACK);
                        setColor(grandparent, RED);
                        node = grandparent;
                    } else {
                        if (node == getRight(parent)) {
                            node = parent;
                            rotateLeft(node);
                            parent = getParent(node);
                            grandparent = getParent(parent);
                        }

                        setColor(parent, BLACK);
                        setColor(grandparent, RED);
                        rotateRight(grandparent);
                    }
                } else {
                    Node uncle = getLeft(grandparent);

                    if (getColor(uncle) == RED) {
                        setColor(parent, BLACK);
                        setColor(uncle, BLACK);
                        setColor(grandparent, RED);
                        node = grandparent;
                    } else {
                        if (node == getLeft(parent)) {
                            node = parent;
                            rotateRight(node);
                            parent = getParent(node);
                            grandparent = getParent(parent);
                        }

                        setColor(parent, BLACK);
                        setColor(grandparent, RED);
                        rotateLeft(grandparent);
                    }
                }
            }

            setColor(root, BLACK);
        }

        private void rotateLeft(Node node) {
            Node rightChild = node.right;
            node.right = rightChild.left;

            if (rightChild.left != null) {
                rightChild.left.parent = node;
            }

            rightChild.parent = node.parent;

            if (node.parent == null) {
                root = rightChild;
            } else if (node == node.parent.left) {
                node.parent.left = rightChild;
            } else {
                node.parent.right = rightChild;
            }

            rightChild.left = node;
            node.parent = rightChild;
        }

        private void rotateRight(Node node) {
            Node leftChild = node.left;
            node.left = leftChild.right;

            if (leftChild.right != null) {
                leftChild.right.parent = node;
            }

            leftChild.parent = node.parent;

            if (node.parent == null) {
                root = leftChild;
            } else if (node == node.parent.left) {
                node.parent.left = leftChild;
            } else {
                node.parent.right = leftChild;
            }

            leftChild.right = node;
            node.parent = leftChild;
        }

        private Node getParent(Node node) {
            return node == null ? null : node.parent;
        }

        private Node getLeft(Node node) {
            return node == null ? null : node.left;
        }

        private Node getRight(Node node) {
            return node == null ? null : node.right;
        }

        private boolean getColor(Node node) {
            return node == null ? BLACK : node.color;
        }

        private void setColor(Node node, boolean color) {
            if (node != null) {
                node.color = color;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfElements = scanner.nextInt();
        RedBlackTree tree = new RedBlackTree();

        for (int i = 0; i < numberOfElements; i++) {
            tree.insert(scanner.nextInt());
        }

        System.out.println("INORDER");
        tree.printInorder();

        System.out.println("PREORDER");
        tree.printPreorder();

        scanner.close();
    }
}
