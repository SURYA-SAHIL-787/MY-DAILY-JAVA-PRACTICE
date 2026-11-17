import java.util.HashMap;
import java.util.Map;

public class FirstRepeatingWithinK {
    public static int findFirstDuplicate(int[] nums, int k) {
        Map<Integer, Integer> lastSeenIndex = new HashMap<>();
        int earliestIndex = Integer.MAX_VALUE;
        int result = -1;

        for (int i = 0; i < nums.length; i++) {
            if (lastSeenIndex.containsKey(nums[i])) {
                int prevIndex = lastSeenIndex.get(nums[i]);
                if (i - prevIndex <= k && prevIndex < earliestIndex) {
                    earliestIndex = prevIndex;
                    result = nums[i];
                }
            }
            lastSeenIndex.put(nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 4};
        int k = 2;
        System.out.println("Output: " + findFirstDuplicate(nums, k)); // Output: 1
    }
}
