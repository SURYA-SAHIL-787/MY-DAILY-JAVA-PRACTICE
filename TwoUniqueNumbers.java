public class TwoUniqueNumbers {
    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 9, 2, 4};

        int xor = 0;

        for (int num : arr) {
            xor ^= num;
        }

        int rightMostSetBit = xor & -xor;

        int num1 = 0;
        int num2 = 0;

        for (int num : arr) {
            if ((num & rightMostSetBit) != 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        System.out.println("Unique numbers: " + num1 + " and " + num2);
    }
}
