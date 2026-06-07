import java.util.HashMap;

public class SearchHashMapKey {
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();

        students.put(101, "Ravi");
        students.put(102, "Aman");
        students.put(103, "Neha");

        int key = 102;

        if (students.containsKey(key)) {
            System.out.println("Key found: " + key);
        } else {
            System.out.println("Key not found");
        }
    }
}
