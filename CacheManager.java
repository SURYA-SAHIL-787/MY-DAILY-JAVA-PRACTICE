import java.util.HashMap;
import java.util.Map;

class KeyNotFoundException extends Exception {
    public KeyNotFoundException(String msg) {
        super(msg);
    }
}

interface Cache {
    void put(String key, String value);
    String get(String key) throws KeyNotFoundException;
}

class MemoryCache implements Cache {
    private final Map<String, String> store = new HashMap<>();

    @Override
    public synchronized void put(String key, String value) {
        store.put(key, value);
    }

    @Override
    public synchronized String get(String key) throws KeyNotFoundException {
        if (!store.containsKey(key)) {
            throw new KeyNotFoundException("Key missing: " + key);
        }
        return store.get(key);
    }
}

public class CacheManager {
    public static void main(String[] args) {
        Cache cache = new MemoryCache();

        Thread writer = new Thread(() -> cache.put("ID_1", "Active"));
        Thread reader = new Thread(() -> {
            try {
                System.out.println("Value: " + cache.get("ID_2"));
            } catch (KeyNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });

        writer.start();
        reader.start();
    }
}
