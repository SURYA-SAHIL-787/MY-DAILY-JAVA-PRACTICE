import java.util.*;

public class SwapLastTwoDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int last = n % 10;
        int secondLast = (n / 10) % 10;
        int remaining = n / 100;

        int result = remaining * 100 + last * 10 + secondLast;
        System.out.println(result);
    }
}
