public class PowerOfTwoChecker {
    public static void main(String[] args) {
        int num = 16;

        if (num > 0 && (num & (num - 1)) == 0) {
            System.out.println(num + " is a power of two");
        } else {
            System.out.println(num + " is not a power of two");
        }
    }
}
