public class EvenOddBit {
    public static void main(String[] args) {
        int n = 17;

        if ((n & 1) == 0) {
            System.out.println(n + " is Even");
        } else {
            System.out.println(n + " is Odd");
        }
    }
}
