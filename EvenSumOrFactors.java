import java.util.*;

public class EvenSumOrFactors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n % 2 == 0) {
            int sum = 0;
            for (int i = 2; i <= n; i += 2) {
                sum += i;
            }
            System.out.println(sum);
        } else {
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    System.out.print(i + " ");
                }
            }
        }
    }
}
