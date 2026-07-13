import java.util.Scanner;

public class CheckEqual {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input two integers
        System.out.print("Enter first integer: ");
        int num1 = sc.nextInt();

        System.out.print("Enter second integer: ");
        int num2 = sc.nextInt();

        // Check equality
        if (num1 == num2) {
            System.out.println("Both numbers are equal.");
        } else {
            System.out.println("The numbers are not equal.");
        }

        sc.close();
    }
}
