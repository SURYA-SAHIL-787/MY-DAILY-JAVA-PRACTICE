import java.util.*;

public class FrequencyCounter {
    public static void main(String[] args) {
        int[] arr = {4, 2, 4, 3, 2, 4};

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        System.out.println(map);
    }
}
