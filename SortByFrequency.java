import java.util.*;

public class SortByFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Integer[] arr = new Integer[n];

        Map<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }

        Arrays.sort(arr, (a, b) -> {
            if (!freq.get(a).equals(freq.get(b))) {
                return freq.get(b) - freq.get(a);
            }
            return a - b;
        });

        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
