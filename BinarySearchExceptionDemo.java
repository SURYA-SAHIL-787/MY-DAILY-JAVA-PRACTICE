public class BinarySearchExceptionDemo {

    static int binarySearch(int[] arr, int target) throws Exception {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        throw new Exception("Element not found");
    }

    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40, 50};

        try {
            int index = binarySearch(arr, 35);
            System.out.println("Element found at index: " + index);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
