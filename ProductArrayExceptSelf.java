import java.util.*;

public class ProductArrayExceptSelf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int prefix = 1;

        for (int i = 0; i < n; i++) {
            ans[i] = prefix;
            prefix *= arr[i];
        }

        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= suffix;
            suffix *= arr[i];
        }

        for (int x : ans) {
            System.out.print(x + " ");
        }
    }
}
