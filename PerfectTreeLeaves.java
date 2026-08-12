import java.util.Scanner;

public class PerfectTreeLeaves {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter height of perfect binary tree: ");
        int height = sc.nextInt();

        int leafNodes = (int) Math.pow(2, height);

        System.out.println("Number of leaf nodes = " + leafNodes);

        sc.close();
    }
}
