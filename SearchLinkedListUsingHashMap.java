import java.util.HashMap;
import java.util.LinkedList;

public class SearchLinkedListUsingHashMap {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("Java");
        list.add("Python");
        list.add("C++");
        list.add("JavaScript");

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }

        String search = "C++";

        if (map.containsKey(search)) {
            System.out.println(search + " found at index " + map.get(search));
        } else {
            System.out.println(search + " not found");
        }
    }
}
