import java.util.HashMap;
import java.util.Map;

public class DeleteAndEarnProblem {

    public static int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> points = new HashMap<>();
        int maximumValue = 0;

        for (int number : nums) {
            points.put(
                number,
                points.getOrDefault(number, 0) + number
            );

            maximumValue = Math.max(maximumValue, number);
        }

        int previousTwo = 0;
        int previousOne = 0;

        for (int value = 0; value <= maximumValue; value++) {
            int currentPoints = points.getOrDefault(value, 0);

            int take = previousTwo + currentPoints;
            int skip = previousOne;

            int current = Math.max(take, skip);

            previousTwo = previousOne;
            previousOne = current;
        }

        return previousOne;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};

        System.out.println(
            "Maximum points: " + deleteAndEarn(nums)
        );
    }
}
