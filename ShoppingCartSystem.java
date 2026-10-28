import java.util.*;

class Product {
    String productId;
    String name;
    double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}

class ShoppingCart {
    private Map<String, Integer> cartMap = new HashMap<>(); // productId -> quantity
    private Map<String, Product> catalog = new HashMap<>(); // productId -> Product details

    public void addProductToCatalog(Product product) {
        catalog.put(product.productId, product);
    }

    public void addItem(String productId, int qty) {
        if (!catalog.containsKey(productId)) {
            throw new IllegalArgumentException("Product not found in catalog");
        }
        cartMap.put(productId, cartMap.getOrDefault(productId, 0) + qty);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : cartMap.entrySet()) {
            Product p = catalog.get(entry.getKey());
            total += p.price * entry.getValue();
        }
        return total;
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProductToCatalog(new Product("P1", "Wireless Mouse", 799.0));
        cart.addProductToCatalog(new Product("P2", "Mechanical Keyboard", 2999.0));

        cart.addItem("P1", 2);
        cart.addItem("P2", 1);

        System.out.println("Total Cart Value: ₹" + cart.calculateTotal());
    }
}
