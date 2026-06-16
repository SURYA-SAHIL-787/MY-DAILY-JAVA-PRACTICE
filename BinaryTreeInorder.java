import java.util.*;

public class BinaryTreeInorder {
    static int[] value;
    static int[] left;
    static int[] right;

    public static void inorder(int node) {
        if (node == -1) {
            return;
        }

        inorder(left[node]);
        System.out.print(value[node] + " ");
        inorder(right[node]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        value = new int[n];
        left = new int[n];
        right = new int[n];

        System.out.println("Enter value, left child, right child for each node:");
        System.out.println("Use -1 if child does not exist");

        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
            left[i] = sc.nextInt();
            right[i] = sc.nextInt();
        }

        System.out.print("Enter root node index: ");
        int root = sc.nextInt();

        System.out.println("Inorder Traversal:");
        inorder(root);

        sc.close();
    }
}
