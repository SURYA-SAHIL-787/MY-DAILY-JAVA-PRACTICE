import java.util.*;

public class SumMultiplesOfX {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        int start = Math.min(a, b);
        int end = Math.max(a, b);
        int sum = 0;

        for (int i = start; i <= end; i++) {
            if (i % x == 0) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}
