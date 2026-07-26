import java.time.LocalDate;
import java.util.*;

class ProductBatch {
    private final String product;
    private final int quantity;
    private final LocalDate expiryDate;

    ProductBatch(
            String product,
            int quantity,
            LocalDate expiryDate
    ) {
        this.product = product;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    String getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return product + " x" + quantity
                + " expires " + expiryDate;
    }
}

class Inventory {
    private final TreeMap<LocalDate, List<ProductBatch>>
            expiryIndex = new TreeMap<>();

    void addBatch(ProductBatch batch) {
        expiryIndex
                .computeIfAbsent(
                        batch.getExpiryDate(),
                        date -> new ArrayList<>()
                )
                .add(batch);
    }

    void showExpiringBy(LocalDate date) {
        NavigableMap<LocalDate, List<ProductBatch>> result =
                expiryIndex.headMap(date, true);

        if (result.isEmpty()) {
            System.out.println(
                    "No batches expiring by " + date
            );
            return;
        }

        result.forEach((expiry, batches) ->
                batches.forEach(System.out::println));
    }

    int totalQuantity(String product) {
        return expiryIndex.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(batch ->
                        batch.getProduct()
                                .equalsIgnoreCase(product))
                .mapToInt(ProductBatch::getQuantity)
                .sum();
    }
}

public class InventoryExpiryTracker {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addBatch(new ProductBatch(
                "Milk", 20,
                LocalDate.of(2026, 7, 28)
        ));

        inventory.addBatch(new ProductBatch(
                "Bread", 15,
                LocalDate.of(2026, 7, 27)
        ));

        inventory.addBatch(new ProductBatch(
                "Milk", 10,
                LocalDate.of(2026, 8, 2)
        ));

        inventory.showExpiringBy(
                LocalDate.of(2026, 7, 30)
        );

        System.out.println(
                "Milk stock: "
                        + inventory.totalQuantity("Milk")
        );
    }
}
