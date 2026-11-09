public class ShortestUnsortedWindow {
    public static int[] findWindow(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left < n - 1 && nums[left] <= nums[left + 1]) left++;
        if (left == n - 1) return new int[] {-1, -1};

        while (right > 0 && nums[right] >= nums[right - 1]) right--;

        int windowMin = nums[left];
        int windowMax = nums[left];

        for (int i = left; i <= right; i++) {
            windowMin = Math.min(windowMin, nums[i]);
            windowMax = Math.max(windowMax, nums[i]);
        }

        while (left > 0 && nums[left - 1] > windowMin) left--;
        while (right < n - 1 && nums[right + 1] < windowMax) right++;

        return new int[] {left, right};
    }

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] window = findWindow(nums);
        System.out.println(window[0] + " to " + window[1]); // 1 to 5
    }
}
