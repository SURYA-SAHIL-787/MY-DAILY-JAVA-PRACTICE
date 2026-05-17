import java.util.*;

public class RemoveLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedList<String> names = new LinkedList<>();

        System.out.print("Enter number of names: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name: ");
            names.add(sc.nextLine());
        }

        System.out.print("Enter name to remove: ");
        String removeName = sc.nextLine();

        boolean removed = names.remove(removeName);

        if (removed) {
            System.out.println(removeName + " removed successfully");
        } else {
            System.out.println(removeName + " not found");
        }

        System.out.println("Final LinkedList:");
        System.out.println(names);

        sc.close();
    }
}
