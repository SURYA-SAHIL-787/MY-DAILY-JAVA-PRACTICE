import java.util.*;

public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums) {
            int position = lowerBound(list, num);

            if (position == list.size()) {
                list.add(num);
            } else {
                list.set(position, num);
            }
        }

        return list.size();
    }

    private static int lowerBound(ArrayList<Integer> list, int target) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        int result = lengthOfLIS(nums);

        System.out.println("Length of LIS: " + result);
    }
}
