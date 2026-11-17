import java.util.HashMap;
import java.util.Map;

public class EqualEvensAndOdds {
    public static int countSubarrays(int[] nums) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // Base case for balance = 0
        
        int balance = 0;
        int totalSubarrays = 0;

        for (int num : nums) {
            // Treat even as +1 and odd as -1
            balance += (num % 2 == 0) ? 1 : -1;
            
            if (prefixCount.containsKey(balance)) {
                totalSubarrays += prefixCount.get(balance);
            }
            prefixCount.put(balance, prefixCount.getOrDefault(balance, 0) + 1);
        }
        return totalSubarrays;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println("Output: " + countSubarrays(nums)); // Output: 4
    }
}
