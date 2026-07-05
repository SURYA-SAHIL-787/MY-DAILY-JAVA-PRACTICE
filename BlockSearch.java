public class BlockSearch {
    public static int search(int[] arr, int target, int blockSize) {
        int n = arr.length;

        if (n == 0) {
            return -1;
        }

        int start = 0;
        int end = blockSize - 1;

        while (start < n && arr[Math.min(end, n - 1)] < target) {
            start += blockSize;
            end += blockSize;
        }

        for (int i = start; i <= Math.min(end, n - 1); i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 8, 12, 14, 17, 25, 29, 31, 36};
        int target = 25;
        int blockSize = 3;

        System.out.println(search(arr, target, blockSize));
    }
}
