import java.util.*;

public class SearchArrayList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter element: ");
            numbers.add(sc.nextInt());
        }

        System.out.print("Enter element to search: ");
        int key = sc.nextInt();

        if (numbers.contains(key)) {
            System.out.println(key + " is found in ArrayList");
        } else {
            System.out.println(key + " is not found in ArrayList");
        }

        sc.close();
    }
}
