import java.util.*;

public class ReverseFirstKElements {
    public static void reverseFirstK(Queue<Integer> queue, int k) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < k; i++) {
            stack.push(queue.remove());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        int remaining = queue.size() - k;
        for (int i = 0; i < remaining; i++) {
            queue.add(queue.remove());
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

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        reverseFirstK(queue, k);

        System.out.println("Queue after reversing first " + k + " elements:");
        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }

        sc.close();
    }
}
