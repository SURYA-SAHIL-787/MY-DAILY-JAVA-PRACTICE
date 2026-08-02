import java.util.Arrays;

public class StudentPerformanceSystem {

    static class Student {
        int id;
        String name;
        int[] marks;
        double average;

        Student(int id, String name, int[] marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
            this.average = calculateAverage();
        }

        private double calculateAverage() {
            int sum = 0;

            for (int mark : marks) {
                sum += mark;
            }

            return marks.length == 0
                    ? 0.0
                    : (double) sum / marks.length;
        }

        @Override
        public String toString() {
            return id + " - " + name
                    + " " + Arrays.toString(marks)
                    + String.format(" Average: %.2f", average);
        }
    }

    // ---------------- BST BY STUDENT ID ----------------

    static class BSTNode {
        Student student;
        BSTNode left;
        BSTNode right;

        BSTNode(Student student) {
            this.student = student;
        }
    }

    static class StudentBST {
        BSTNode root;

        void insert(Student student) {
            root = insert(root, student);
        }

        private BSTNode insert(BSTNode node, Student student) {
            if (node == null) {
                return new BSTNode(student);
            }

            if (student.id < node.student.id) {
                node.left = insert(node.left, student);
            } else if (student.id > node.student.id) {
                node.right = insert(node.right, student);
            }

            return node;
        }

        Student search(int id) {
            BSTNode current = root;

            while (current != null) {
                if (id == current.student.id) {
                    return current.student;
                }

                if (id < current.student.id) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        void displayById() {
            inorder(root);
        }

        private void inorder(BSTNode node) {
            if (node != null) {
                inorder(node.left);
                System.out.println(node.student);
                inorder(node.right);
            }
        }
    }

    // ---------------- AVL BY AVERAGE MARKS ----------------

    static class AVLNode {
        Student student;
        AVLNode left;
        AVLNode right;
        int height;

        AVLNode(Student student) {
            this.student = student;
            this.height = 1;
        }
    }

    static class StudentAVL {
        AVLNode root;

        void insert(Student student) {
            root = insert(root, student);
        }

        private AVLNode insert(AVLNode node, Student student) {
            if (node == null) {
                return new AVLNode(student);
            }

            if (compare(student, node.student) < 0) {
                node.left = insert(node.left, student);
            } else {
                node.right = insert(node.right, student);
            }

            updateHeight(node);

            int balance = getBalance(node);

            // Left-Left
            if (balance > 1
                    && compare(student, node.left.student) < 0) {
                return rotateRight(node);
            }

            // Right-Right
            if (balance < -1
                    && compare(student, node.right.student) > 0) {
                return rotateLeft(node);
            }

            // Left-Right
            if (balance > 1
                    && compare(student, node.left.student) > 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            // Right-Left
            if (balance < -1
                    && compare(student, node.right.student) < 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        private int compare(Student first, Student second) {
            int averageComparison =
                    Double.compare(first.average, second.average);

            if (averageComparison != 0) {
                return averageComparison;
            }

            return Integer.compare(first.id, second.id);
        }

        private int height(AVLNode node) {
            return node == null ? 0 : node.height;
        }

        private void updateHeight(AVLNode node) {
            node.height =
                    1 + Math.max(height(node.left), height(node.right));
        }

        private int getBalance(AVLNode node) {
            return node == null
                    ? 0
                    : height(node.left) - height(node.right);
        }

        private AVLNode rotateRight(AVLNode y) {
            AVLNode x = y.left;
            AVLNode middle = x.right;

            x.right = y;
            y.left = middle;

            updateHeight(y);
            updateHeight(x);

            return x;
        }

        private AVLNode rotateLeft(AVLNode x) {
            AVLNode y = x.right;
            AVLNode middle = y.left;

            y.left = x;
            x.right = middle;

            updateHeight(x);
            updateHeight(y);

            return y;
        }

        void displayByAverageDescending() {
            reverseInorder(root);
        }

        private void reverseInorder(AVLNode node) {
            if (node != null) {
                reverseInorder(node.right);

                System.out.printf(
                        "%-12s Average: %.2f%n",
                        node.student.name,
                        node.student.average
                );

                reverseInorder(node.left);
            }
        }
    }

    // ---------------- LINKED LIST FOR HISTORY ----------------

    static class HistoryNode {
        String action;
        HistoryNode next;

        HistoryNode(String action) {
            this.action = action;
        }
    }

    static class ActivityLinkedList {
        HistoryNode head;
        HistoryNode tail;

        void add(String action) {
            HistoryNode newNode = new HistoryNode(action);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        void display() {
            HistoryNode current = head;

            while (current != null) {
                System.out.println("- " + current.action);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {

        Student[] students = {
                new Student(
                        104,
                        "Anika",
                        new int[]{88, 91, 84}
                ),
                new Student(
                        101,
                        "Bharat",
                        new int[]{76, 82, 79}
                ),
                new Student(
                        107,
                        "Charan",
                        new int[]{93, 89, 96}
                ),
                new Student(
                        102,
                        "Divya",
                        new int[]{85, 87, 90}
                ),
                new Student(
                        105,
                        "Eshan",
                        new int[]{70, 74, 72}
                )
        };

        StudentBST idIndex = new StudentBST();
        StudentAVL performanceIndex = new StudentAVL();
        ActivityLinkedList history = new ActivityLinkedList();

        for (Student student : students) {
            idIndex.insert(student);
            performanceIndex.insert(student);

            history.add(
                    "Added student: " + student.name
            );
        }

        System.out.println("STUDENTS SORTED BY ID (BST)");
        idIndex.displayById();

        System.out.println(
                "\nSTUDENTS SORTED BY AVERAGE (AVL)"
        );
        performanceIndex.displayByAverageDescending();

        int searchId = 102;
        Student found = idIndex.search(searchId);

        System.out.println(
                "\nSEARCH RESULT FOR ID " + searchId
        );

        if (found == null) {
            System.out.println("Student not found");
        } else {
            System.out.println(found);
        }

        history.add(
                "Searched for student ID: " + searchId
        );

        System.out.println(
                "\nACTIVITY HISTORY (LINKED LIST)"
        );
        history.display();
    }
}
