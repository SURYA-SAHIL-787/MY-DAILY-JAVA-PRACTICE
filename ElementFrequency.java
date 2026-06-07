import java.util.HashMap;

public class ElementFrequency {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3};

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        System.out.println("Element Frequency: " + map);
    }
}
