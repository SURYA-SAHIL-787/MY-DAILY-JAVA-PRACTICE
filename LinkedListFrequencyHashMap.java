import java.util.HashMap;
import java.util.LinkedList;

public class LinkedListFrequencyHashMap {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(10);
        list.add(30);
        list.add(20);
        list.add(10);

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : list) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        System.out.println("Frequency: " + map);
    }
}
