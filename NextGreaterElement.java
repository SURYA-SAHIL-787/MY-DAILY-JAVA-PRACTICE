import java.util.*;

public class NextGreaterElement {

    public static void main(String[] args) {

        int[] arr = {4, 5, 2, 10, 8};

        int n = arr.length;

        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Start from right side
        for (int i = n - 1; i >= 0; i--) {

            // Remove smaller elements
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            // If stack is empty, no greater element
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }

            stack.push(arr[i]);
        }

        System.out.println("Next Greater Elements:");

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
