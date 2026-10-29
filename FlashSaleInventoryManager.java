import java.util.*;
import java.util.concurrent.*;

class FlashSaleProduct {
    String productId;
    String name;
    int stockCount;

    public FlashSaleProduct(String productId, String name, int stockCount) {
        this.productId = productId;
        this.name = name;
        this.stockCount = stockCount;
    }
}

class PurchaseRequest {
    String userId;
    String productId;

    public PurchaseRequest(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }
}

public class FlashSaleInventoryManager {
    private Map<String, FlashSaleProduct> inventory = new HashMap<>();
    private Queue<PurchaseRequest> requestQueue = new ConcurrentLinkedQueue<>();

    public void addProduct(String id, String name, int stock) {
        inventory.put(id, new FlashSaleProduct(id, name, stock));
    }

    public void submitOrder(String userId, String productId) {
        requestQueue.offer(new PurchaseRequest(userId, productId));
    }

    public void processQueue() {
        while (!requestQueue.isEmpty()) {
            PurchaseRequest req = requestQueue.poll();
            FlashSaleProduct product = inventory.get(req.productId);

            if (product != null && product.stockCount > 0) {
                product.stockCount--;
                System.out.println("SUCCESS: User " + req.userId + " bought " + product.name + ". Remaining stock: " + product.stockCount);
            } else {
                System.out.println("SOLD OUT: User " + req.userId + " failed to buy product " + req.productId);
            }
        }
    }

    public static void main(String[] args) {
        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();
        manager.addProduct("IP15", "iPhone 15 Pro", 2);

        // Simulating high concurrency flash sale requests arriving in a queue
        manager.submitOrder("User_A", "IP15");
        manager.submitOrder("User_B", "IP15");
        manager.submitOrder("User_C", "IP15");
        manager.submitOrder("User_D", "IP15");

        manager.processQueue();
    }
}
