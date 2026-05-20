import java.util.*;

public class MaxCircularSubarrayWithOneDeletion {
    public static int maxSum(int[] arr) {
        int n = arr.length;

        int normal = kadane(arr);

        int total = 0;
        for (int x : arr) total += x;

        int minSub = minKadane(arr);
        int circular = total - minSub;

        int oneDelete = maxSubarrayWithOneDeletion(arr);

        if (circular == 0) return Math.max(normal, oneDelete);

        return Math.max(Math.max(normal, circular), oneDelete);
    }

    private static int kadane(int[] arr) {
        int curr = arr[0], best = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            best = Math.max(best, curr);
        }

        return best;
    }

    private static int minKadane(int[] arr) {
        int curr = arr[0], best = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curr = Math.min(arr[i], curr + arr[i]);
            best = Math.min(best, curr);
        }

        return best;
    }

    private static int maxSubarrayWithOneDeletion(int[] arr) {
        int n = arr.length;

        int noDelete = arr[0];
        int oneDelete = 0;
        int best = arr[0];

        for (int i = 1; i < n; i++) {
            oneDelete = Math.max(noDelete, oneDelete + arr[i]);
            noDelete = Math.max(arr[i], noDelete + arr[i]);
            best = Math.max(best, Math.max(noDelete, oneDelete));
        }

        return best;
    }

    public static void main(String[] args) {
        int[] arr = {8, -1, -3, 8, -6, 7, -2, 9};
        System.out.println(maxSum(arr));
    }
}
