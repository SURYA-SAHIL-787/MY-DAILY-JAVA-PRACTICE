import java.util.*;

public class FirstFourFactors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;

        for (int i = 1; i <= n && count < 4; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                count++;
            }
        }
    }
}
