import java.util.*;

public class MaximizeArraySumAfterKNegations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        for (int i = 0; i < n && k > 0; i++) {
            if (arr[i] < 0) {
                arr[i] = -arr[i];
                k--;
            }
        }

        Arrays.sort(arr);

        if (k % 2 == 1) {
            arr[0] = -arr[0];
        }

        int sum = 0;
        for (int x : arr) {
            sum += x;
        }

        System.out.println(sum);
    }
}
