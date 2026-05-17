import java.util.*;

public class ArrayListToLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> arrayList = new ArrayList<>();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter element: ");
            arrayList.add(sc.nextInt());
        }

        LinkedList<Integer> linkedList = new LinkedList<>(arrayList);

        System.out.println("ArrayList:");
        System.out.println(arrayList);

        System.out.println("LinkedList:");
        System.out.println(linkedList);

        sc.close();
    }
}
