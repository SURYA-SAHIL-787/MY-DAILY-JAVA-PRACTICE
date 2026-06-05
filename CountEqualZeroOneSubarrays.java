import java.util.*;

public class CountEqualZeroOneSubarrays {
    public static int countSubarrays(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int count = 0;

        map.put(0, 1);

        for (int num : arr) {
            if (num == 0) {
                sum += -1;
            } else {
                sum += 1;
            }

            if (map.containsKey(sum)) {
                count += map.get(sum);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 0, 1, 1, 0};

        System.out.println("Count: " + countSubarrays(arr));
    }
}
