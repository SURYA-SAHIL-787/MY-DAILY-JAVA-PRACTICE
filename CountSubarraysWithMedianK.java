import java.util.*;

public class CountSubarraysWithMedianK {
    public static int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int index = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                index = i;
                break;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int balance = 0;

        map.put(0, 1);

        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] < k) balance--;
            else balance++;

            map.put(balance, map.getOrDefault(balance, 0) + 1);
        }

        int answer = 0;
        balance = 0;

        for (int i = index; i < n; i++) {
            if (nums[i] < k) balance--;
            else if (nums[i] > k) balance++;

            answer += map.getOrDefault(-balance, 0);
            answer += map.getOrDefault(1 - balance, 0);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 4, 5};
        int k = 4;

        System.out.println(countSubarrays(nums, k));
    }
}
