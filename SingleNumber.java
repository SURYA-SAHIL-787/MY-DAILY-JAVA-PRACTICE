public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 4, 5, 3, 4};

        int result = 0;

        for (int num : arr) {
            result = result ^ num;
        }

        System.out.println("The non-repeating number is: " + result);
    }
}
