import java.util.Arrays;

public class MoveArrayElementsZerosToEnd {
    public static void moveZeros(int[] arr) {
        int index = 0;

        for (int num : arr) {
            if (num != 0) {
                arr[index] = num;
                index++;
            }
        }

        while (index < arr.length) {
            arr[index] = 0;
            index++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 5, 0, 3, 8, 0, 1};

        moveZeros(arr);

        System.out.println("Array after moving zeros: " + Arrays.toString(arr));
    }
}
