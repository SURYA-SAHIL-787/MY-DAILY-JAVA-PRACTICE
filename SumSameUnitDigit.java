import java.util.*;

public class SumSameUnitDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int digit = sc.nextInt();
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            if (x % 10 == digit) {
                sum += x;
            }
        }

        System.out.println(sum);
    }
}
