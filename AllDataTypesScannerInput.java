import java.util.Scanner;

public class AllDataTypesScannerInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Byte
        System.out.print("Enter a byte value: ");
        byte b = sc.nextByte();
        System.out.println("Byte value : " + b);

        // Short
        System.out.print("Enter a short value : ");
        short s = sc.nextShort();
        System.out.println("Short value : " + s);

        // Long
        System.out.print("Enter a long value : ");
        long l = sc.nextLong();
        System.out.println("Long value : " + l);

        // Float
        System.out.print("Enter a float value : ");
        float f = sc.nextFloat();
        System.out.println("Float value : " + f);

        // Double
        System.out.print("Enter a double value: ");
        double d = sc.nextDouble();
        System.out.println("Double value : " + d);

        // Character
        System.out.print("Enter a character: ");
        char c = sc.next().charAt(0);
        System.out.println("Character : " + c);

        sc.close();
    }
}

