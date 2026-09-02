import java.util.HashMap;

public class StudentRankingSystem {

    static class Student {
        int rollNumber;
        String name;
        int marks;

        Student(int rollNumber, String name, int marks) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return rollNumber + " - " + name + " - " + marks;
        }
    }

    static class AVLNode {
        Student student;
        AVLNode left;
        AVLNode right;
        int height;

        AVLNode(Student student) {
            this.student = student;
            height = 1;
        }
    }

    private final HashMap<Integer, Student> studentMap = new HashMap<>();
    private AVLNode root;

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0
                : height(node.left) - height(node.right);
    }

    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    private int compare(Student first, Student second) {
        if (first.marks != second.marks) {
            return Integer.compare(first.marks, second.marks);
        }

        return Integer.compare(
                first.rollNumber,
                second.rollNumber
        );
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode newRoot = node.left;
        AVLNode temporary = newRoot.right;

        newRoot.right = node;
        node.left = temporary;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode newRoot = node.right;
        AVLNode temporary = newRoot.left;

        newRoot.left = node;
        node.right = temporary;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
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

        // Left-left case
        if (balance > 1 &&
                compare(student, node.left.student) < 0) {
            return rotateRight(node);
        }

        // Right-right case
        if (balance < -1 &&
                compare(student, node.right.student) > 0) {
            return rotateLeft(node);
        }

        // Left-right case
        if (balance > 1) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right-left case
        if (balance < -1) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void addStudent(
            int rollNumber,
            String name,
            int marks
    ) {
        if (studentMap.containsKey(rollNumber)) {
            System.out.println("Roll number already exists.");
            return;
        }

        Student student =
                new Student(rollNumber, name, marks);

        studentMap.put(rollNumber, student);
        root = insert(root, student);
    }

    public void searchStudent(int rollNumber) {
        Student student = studentMap.get(rollNumber);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found: " + student);
        }
    }

    private void displayDescending(AVLNode node) {
        if (node == null) {
            return;
        }

        displayDescending(node.right);
        System.out.println(node.student);
        displayDescending(node.left);
    }

    public void displayRanking() {
        System.out.println("\nStudent ranking:");
        displayDescending(root);
    }

    public static void main(String[] args) {
        StudentRankingSystem system =
                new StudentRankingSystem();

        system.addStudent(101, "Surya", 88);
        system.addStudent(102, "Ravi", 95);
        system.addStudent(103, "Asha", 82);
        system.addStudent(104, "Neha", 91);

        system.searchStudent(103);
        system.displayRanking();
    }
}
