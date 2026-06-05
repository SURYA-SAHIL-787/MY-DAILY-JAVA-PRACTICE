import java.util.*;

public class MinimumWindowUniqueElements {
    public static int minimumWindow(int[] arr) {
        HashSet<Integer> uniqueSet = new HashSet<>();

        for (int num : arr) {
            uniqueSet.add(num);
        }

        int requiredUniqueCount = uniqueSet.size();

        HashMap<Integer, Integer> windowMap = new HashMap<>();

        int left = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < arr.length; right++) {
            windowMap.put(arr[right], windowMap.getOrDefault(arr[right], 0) + 1);

            while (windowMap.size() == requiredUniqueCount) {
                minLength = Math.min(minLength, right - left + 1);

                windowMap.put(arr[left], windowMap.get(arr[left]) - 1);

                if (windowMap.get(arr[left]) == 0) {
                    windowMap.remove(arr[left]);
                }

                left++;
            }
        }

        return minLength;
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 7, 3, 1, 3, 4, 1};

        System.out.println("Minimum Window Length: " + minimumWindow(arr));
    }
}
