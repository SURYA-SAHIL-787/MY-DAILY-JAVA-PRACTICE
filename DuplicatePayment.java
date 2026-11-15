import java.util.HashSet;
import java.util.Set;

public class DuplicatePayment {
    public static void main(String[] args) {
        int[] paymentIds = {501, 502, 503, 502, 501};
        Set<Integer> seen = new HashSet<>();

        for (int id : paymentIds) {
            if (!seen.add(id)) {
                System.out.println(id);
                return;
            }
        }

        System.out.println("No duplicate");
    }
}
