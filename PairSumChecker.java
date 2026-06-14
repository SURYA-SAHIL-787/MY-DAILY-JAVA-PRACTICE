import java.util.*;

public class PairSumChecker {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;

        HashSet<Integer> set = new HashSet<>();

        for (int num : arr) {
            int needed = target - num;

            if (set.contains(needed)) {
                System.out.println("Pair exists: " + needed + " and " + num);
                return;
            }

            set.add(num);
        }

        System.out.println("No pair found");
    }
}
