import java.util.*;

public class CountPairsWithXOR {
    public static int countPairs(int[] arr, int x) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int count = 0;

        for (int num : arr) {
            int required = num ^ x;

            if (map.containsKey(required)) {
                count += map.get(required);
            }

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 10, 15, 7, 6};
        int x = 5;

        System.out.println("Number Of Pairs: " + countPairs(arr, x));
    }
}
