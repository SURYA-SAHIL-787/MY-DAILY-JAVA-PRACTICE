import java.util.HashMap;
import java.util.Map;

public class CountSubarraysWithXor {
    public static long count(int[] nums, int target) {
        Map<Integer, Long> prefixCounts = new HashMap<>();
        prefixCounts.put(0, 1L);

        int prefixXor = 0;
        long result = 0;

        for (int value : nums) {
            prefixXor ^= value;
            result += prefixCounts.getOrDefault(prefixXor ^ target, 0L);
            prefixCounts.put(
                prefixXor,
                prefixCounts.getOrDefault(prefixXor, 0L) + 1
            );
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 6, 4};
        System.out.println(count(nums, 6)); // 4
    }
}
