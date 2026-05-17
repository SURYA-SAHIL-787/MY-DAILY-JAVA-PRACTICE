import java.util.*;

public class LinkedListInsert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedList<Integer> list = new LinkedList<>();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter element: ");
            list.add(sc.nextInt());
        }

        System.out.print("Enter element to insert at beginning: ");
        int first = sc.nextInt();

        System.out.print("Enter element to insert at end: ");
        int last = sc.nextInt();

        list.addFirst(first);
        list.addLast(last);

        System.out.println("Final LinkedList:");
        System.out.println(list);

        sc.close();
    }
}
