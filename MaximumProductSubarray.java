public class MaximumProductSubarray {
    public static int findMaximumProduct(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int maxEnding = arr[0];
        int minEnding = arr[0];
        int answer = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];

            if (value < 0) {
                int temp = maxEnding;
                maxEnding = minEnding;
                minEnding = temp;
            }

            maxEnding = Math.max(value, maxEnding * value);
            minEnding = Math.min(value, minEnding * value);
            answer = Math.max(answer, maxEnding);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, -2, 4};
        System.out.println(findMaximumProduct(arr)); // 6
    }
}
