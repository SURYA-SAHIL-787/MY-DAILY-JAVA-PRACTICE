import java.util.*;

public class SortQueueUsingStack {
    public static void sortQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty()) {
            int current = queue.remove();

            while (!stack.isEmpty() && stack.peek() > current) {
                queue.add(stack.pop());
            }

            stack.push(current);
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        int size = queue.size();

        for (int i = 0; i < size; i++) {
            stack.push(queue.remove());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("Enter queue elements:");
        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }

        sortQueue(queue);

        System.out.println("Sorted queue:");
        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }

        sc.close();
    }
}
