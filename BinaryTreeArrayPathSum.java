import java.util.*;

public class BinaryTreeArrayPathSum {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    static boolean pathSum(Node root, int target, ArrayList<Integer> path) {
        if (root == null) return false;

        path.add(root.data);
        target -= root.data;

        if (root.left == null && root.right == null && target == 0) {
            return true;
        }

        if (pathSum(root.left, target, path) || pathSum(root.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);

        ArrayList<Integer> path = new ArrayList<>();

        if (pathSum(root, 22, path)) {
            System.out.println("Path: " + path);
        } else {
            System.out.println("No path found");
        }
    }
}
