public class LongestIncreasingContiguousSubarray {
    public static int findLength(int[] arr) {
        if (arr.length == 0) return 0;

        int current = 1;
        int longest = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                current++;
            } else {
                current = 1;
            }
            longest = Math.max(longest, current);
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 1};
        System.out.println(findLength(arr)); // 3
    }
}
