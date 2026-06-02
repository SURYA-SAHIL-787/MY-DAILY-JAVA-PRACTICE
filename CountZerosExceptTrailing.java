import java.util.*;

public class CountZerosExceptTrailing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;

        while (n % 10 == 0 && n != 0) n /= 10;

        while (n > 0) {
            if (n % 10 == 0) count++;
            n /= 10;
        }

        System.out.println(count);
    }
}
