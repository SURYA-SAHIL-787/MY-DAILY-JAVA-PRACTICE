import java.util.*;

public class FindUniqueElement {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 4, 5, 3, 4};

        int result = 0;

        for (int num : arr) {
            result ^= num;
        }

        System.out.println("Unique element: " + result);
    }
}
