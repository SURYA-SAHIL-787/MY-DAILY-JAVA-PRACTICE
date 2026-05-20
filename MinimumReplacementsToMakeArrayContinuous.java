import java.util.*;

public class MinimumReplacementsToMakeArrayContinuous {
    public static int minOperations(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int[] unique = new int[n];
        int m = 0;

        for (int x : nums) {
            if (m == 0 || unique[m - 1] != x) {
                unique[m++] = x;
            }
        }

        int answer = n;
        int right = 0;

        for (int left = 0; left < m; left++) {
            while (right < m && unique[right] < unique[left] + n) {
                right++;
            }

            int alreadyGood = right - left;
            answer = Math.min(answer, n - alreadyGood);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {8, 5, 9, 9, 8, 4};

        System.out.println(minOperations(nums));
    }
}
