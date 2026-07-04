import java.util.Scanner;

public class CheckIthBit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int n = sc.nextInt();

        System.out.print("Enter bit position: ");
        int i = sc.nextInt();

        if ((n & (1 << i)) != 0) {
            System.out.println("Bit at position " + i + " is set");
        } else {
            System.out.println("Bit at position " + i + " is not set");
        }

        sc.close();
    }
}
