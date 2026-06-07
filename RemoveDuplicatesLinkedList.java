import java.util.HashSet;
import java.util.LinkedList;

public class RemoveDuplicatesLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(5);
        list.add(10);
        list.add(5);
        list.add(20);
        list.add(10);
        list.add(30);

        HashSet<Integer> set = new HashSet<>();
        LinkedList<Integer> result = new LinkedList<>();

        for (int num : list) {
            if (!set.contains(num)) {
                set.add(num);
                result.add(num);
            }
        }

        System.out.println("Original List: " + list);
        System.out.println("After Removing Duplicates: " + result);
    }
}
