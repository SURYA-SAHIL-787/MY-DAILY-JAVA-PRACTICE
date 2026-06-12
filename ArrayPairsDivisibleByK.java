import java.util.*;

public class ArrayPairsDivisibleByK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] rem = new int[k];

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            int r = ((num % k) + k) % k;
            rem[r]++;
        }

        if (rem[0] % 2 != 0) {
            System.out.println("No");
            return;
        }

        for (int i = 1; i <= k / 2; i++) {
            if (i == k - i) {
                if (rem[i] % 2 != 0) {
                    System.out.println("No");
                    return;
                }
            } else {
                if (rem[i] != rem[k - i]) {
                    System.out.println("No");
                    return;
                }
            }
        }

        System.out.println("Yes");
    }
}
