import java.util.Scanner;

public class RedBlackTreeRangeValues {

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

        void printRange(int minimum, int maximum) {
            printRangeValues(root, minimum, maximum);
            System.out.println();
        }

        int countRange(int minimum, int maximum) {
            return countRangeValues(root, minimum, maximum);
        }

        private void printRangeValues(
                Node node,
                int minimum,
                int maximum) {

            if (node == null) {
                return;
            }

            if (node.data > minimum) {
                printRangeValues(node.left, minimum, maximum);
            }

            if (node.data >= minimum
                    && node.data <= maximum) {
                System.out.print(node.data + " ");
            }

            if (node.data < maximum) {
                printRangeValues(node.right, minimum, maximum);
            }
        }

        private int countRangeValues(
                Node node,
                int minimum,
                int maximum) {

            if (node == null) {
                return 0;
            }

            if (node.data < minimum) {
                return countRangeValues(
                        node.right,
                        minimum,
                        maximum
                );
            }

            if (node.data > maximum) {
                return countRangeValues(
                        node.left,
                        minimum,
                        maximum
                );
            }

            return 1
                    + countRangeValues(
                            node.left,
                            minimum,
                            maximum
                    )
                    + countRangeValues(
                            node.right,
                            minimum,
                            maximum
                    );
        }

        private void fixInsertion(Node node) {
            while (node != root
                    && colorOf(parentOf(node)) == RED) {

                Node parent = parentOf(node);
                Node grandparent = parentOf(parent);

                if (parent == leftOf(grandparent)) {
                    Node uncle = rightOf(grandparent);

                    if (colorOf(uncle) == RED) {
                        setColor(parent, BLACK);
                        setColor(uncle, BLACK);
                        setColor(grandparent, RED);
                        node = grandparent;
                    } else {
                        if (node == rightOf(parent)) {
                            node = parent;
                            rotateLeft(node);
                            parent = parentOf(node);
                            grandparent = parentOf(parent);
                        }

                        setColor(parent, BLACK);
                        setColor(grandparent, RED);
                        rotateRight(grandparent);
                    }
                } else {
                    Node uncle = leftOf(grandparent);

                    if (colorOf(uncle) == RED) {
                        setColor(parent, BLACK);
                        setColor(uncle, BLACK);
                        setColor(grandparent, RED);
                        node = grandparent;
                    } else {
                        if (node == leftOf(parent)) {
                            node = parent;
                            rotateRight(node);
                            parent = parentOf(node);
                            grandparent = parentOf(parent);
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

        private Node parentOf(Node node) {
            return node == null ? null : node.parent;
        }

        private Node leftOf(Node node) {
            return node == null ? null : node.left;
        }

        private Node rightOf(Node node) {
            return node == null ? null : node.right;
        }

        private boolean colorOf(Node node) {
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

        int minimum = scanner.nextInt();
        int maximum = scanner.nextInt();

        tree.printRange(minimum, maximum);

        System.out.println(
                "Count: " + tree.countRange(minimum, maximum)
        );

        scanner.close();
    }
}
