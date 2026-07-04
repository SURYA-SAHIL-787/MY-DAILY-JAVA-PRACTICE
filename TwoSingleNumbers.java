public class TwoSingleNumbers {
    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 9, 2, 4};

        int xor = 0;

        for (int num : arr) {
            xor = xor ^ num;
        }

        int rightmostSetBit = xor & -xor;

        int num1 = 0;
        int num2 = 0;

        for (int num : arr) {
            if ((num & rightmostSetBit) == 0) {
                num1 = num1 ^ num;
            } else {
                num2 = num2 ^ num;
            }
        }

        System.out.println("Two non-repeating numbers are: " + num1 + " and " + num2);
    }
}
