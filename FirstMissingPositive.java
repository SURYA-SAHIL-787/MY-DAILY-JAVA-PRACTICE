public class FirstMissingPositive {
    public static int find(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1
                    && nums[i] <= n
                    && nums[nums[i] - 1] != nums[i]) {
                int correctIndex = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return n + 1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, -1, 1};
        System.out.println(find(nums)); // 2
    }
}
