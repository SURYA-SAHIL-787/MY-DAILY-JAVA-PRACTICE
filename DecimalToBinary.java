import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter decimal number: ");
        int n = sc.nextInt();

        String binary = "";

        if (n == 0) {
            binary = "0";
        } else {
            while (n > 0) {
                int bit = n & 1;
                binary = bit + binary;
                n = n >> 1;
            }
        }

        System.out.println("Binary representation: " + binary);

        sc.close();
    }
}
