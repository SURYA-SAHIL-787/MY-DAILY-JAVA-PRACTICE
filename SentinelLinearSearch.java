public class SentinelLinearSearch {
    public static int search(int[] arr, int target) {
        int n = arr.length;

        if (n == 0) {
            return -1;
        }

        int last = arr[n - 1];
        arr[n - 1] = target;

        int i = 0;

        while (arr[i] != target) {
            i++;
        }

        arr[n - 1] = last;

        if (i < n - 1 || arr[n - 1] == target) {
            return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {12, 34, 54, 2, 3};
        int target = 54;

        System.out.println(search(arr, target));
    }
}
