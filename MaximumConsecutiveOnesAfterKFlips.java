import java.util.*;

public class MaximumConsecutiveOnesAfterKFlips {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int left = 0, zeros = 0, maxLen = 0;

        for (int right = 0; right < n; right++) {
            if (arr[right] == 0) zeros++;

            while (zeros > k) {
                if (arr[left] == 0) zeros--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        System.out.println(maxLen);
    }
}
