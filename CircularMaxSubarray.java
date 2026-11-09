public class CircularMaxSubarray {
    public static int maxCircularSum(int[] nums) {
        int total = 0;
        int maxEnding = nums[0], maxSum = nums[0];
        int minEnding = nums[0], minSum = nums[0];

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];

            if (i > 0) {
                maxEnding = Math.max(nums[i], maxEnding + nums[i]);
                maxSum = Math.max(maxSum, maxEnding);

                minEnding = Math.min(nums[i], minEnding + nums[i]);
                minSum = Math.min(minSum, minEnding);
            }
        }

        // If every number is negative, wrapping would incorrectly produce zero.
        return maxSum < 0 ? maxSum : Math.max(maxSum, total - minSum);
    }

    public static void main(String[] args) {
        int[] nums = {8, -1, 3, 4};
        System.out.println(maxCircularSum(nums)); // 15
    }
}
