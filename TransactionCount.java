import java.util.LinkedHashMap;
import java.util.Map;

public class TransactionCount {
    public static void main(String[] args) {
        String[] accounts = {"A101", "A102", "A101", "A103", "A102", "A101"};
        Map<String, Integer> counts = new LinkedHashMap<>();

        for (String account : accounts) {
            counts.put(account, counts.getOrDefault(account, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
