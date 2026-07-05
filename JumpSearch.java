public class JumpSearch {
    public static int search(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.sqrt(n);

        int prev = 0;

        while (prev < n && arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);

            if (prev >= n) {
                return -1;
            }
        }

        while (prev < Math.min(step, n)) {
            if (arr[prev] == target) {
                return prev;
            }
            prev++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20, 25, 30, 35};
        int target = 25;

        System.out.println(search(arr, target));
    }
}
