public class ExponentialSearch {
    public static int search(int[] arr, int target) {
        int n = arr.length;

        if (n == 0) {
            return -1;
        }

        if (arr[0] == target) {
            return 0;
        }

        int i = 1;

        while (i < n && arr[i] <= target) {
            i *= 2;
        }

        return binarySearch(arr, target, i / 2, Math.min(i, n - 1));
    }

    private static int binarySearch(int[] arr, int target, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 8, 16, 32, 64, 128};
        int target = 64;

        System.out.println(search(arr, target));
    }
}
