import java.util.Scanner;

public class PerfectTreeNodes {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter height of perfect binary tree: ");
        int height = sc.nextInt();

        int totalNodes = (int) Math.pow(2, height + 1) - 1;

        System.out.println("Total number of nodes = " + totalNodes);

        sc.close();
    }
}
