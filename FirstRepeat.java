import java.util.HashSet;
import java.util.Set;

public class FirstRepeat {
    public static int firstRepeatIndex(int[] numbers) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (!seen.add(numbers[i])) {
                return i;
            }
        }
        return -1;
    }
}
