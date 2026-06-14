public class KthBitChecker {
    public static void main(String[] args) {
        int num = 10; 
        int k = 1;

        if ((num & (1 << k)) != 0) {
            System.out.println("The " + k + "-th bit is set");
        } else {
            System.out.println("The " + k + "-th bit is not set");
        }
    }
}
