public class MaximumSumSub_array_DP {
    public static int maxSubArray(int[] arr) {
        int n = arr.length;

        int currSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < n; i++) {
            currSum = Math.max(arr[i], currSum + arr[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("Maximum Subarray Sum: " + maxSubArray(arr));
    }
}
