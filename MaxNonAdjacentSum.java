public class MaxNonAdjacentSum {
    public static int maxSum(int[] arr) {
        int n = arr.length;

        if (n == 0) return 0;
        if (n == 1) return arr[0];

        int prev2 = 0;
        int prev1 = arr[0];

        for (int i = 1; i < n; i++) {
            int pick = arr[i] + prev2;
            int notPick = prev1;

            int curr = Math.max(pick, notPick);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 9};

        System.out.println("Maximum Non-Adjacent Sum: " + maxSum(arr));
    }
}
