import java.util.Scanner;

public class BitOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int n = sc.nextInt();

        System.out.print("Enter bit position: ");
        int pos = sc.nextInt();

        int setBit = n | (1 << pos);
        int clearBit = n & ~(1 << pos);
        int toggleBit = n ^ (1 << pos);

        System.out.println("Original number: " + n);
        System.out.println("After setting bit: " + setBit);
        System.out.println("After clearing bit: " + clearBit);
        System.out.println("After toggling bit: " + toggleBit);

        sc.close();
    }
}
