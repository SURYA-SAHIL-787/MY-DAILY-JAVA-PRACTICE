import java.util.HashMap;

public class HashMapExceptionDemo {

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "Apple");
        map.put(2, "Banana");

        try {
            String value = map.get(3);

            if (value == null) {
                throw new NullPointerException("Key not found in HashMap");
            }

            System.out.println(value);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
