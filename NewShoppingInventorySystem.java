import java.util.*;

class Product {
    int id;
    String name;
    int quantity;
    double price;

    Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class InventoryRepository {
    HashMap<Integer, Product> products = new HashMap<>();

    void addProduct(Product product) {
        products.put(product.id, product);
    }

    Product findProduct(int id) {
        return products.get(id);
    }

    Collection<Product> getAllProducts() {
        return products.values();
    }
}

class ShoppingService {

    InventoryRepository repo = new InventoryRepository();
    Stack<Product> orderHistory = new Stack<>();

    void addProduct(Product product) {
        repo.addProduct(product);
        System.out.println("Product Added Successfully.");
    }

    void purchaseProduct(int id) {

        Product product = repo.findProduct(id);

        if (product != null && product.quantity > 0) {
            product.quantity--;
            orderHistory.push(product);
            System.out.println("Purchased : " + product.name);
        } else {
            System.out.println("Product Out Of Stock.");
        }
    }

    void cancelLastOrder() {

        if (orderHistory.isEmpty()) {
            System.out.println("No Orders To Cancel.");
            return;
        }

        Product product = orderHistory.pop();
        product.quantity++;

        System.out.println("Last Order Cancelled.");
    }

    void displayInventory() {

        System.out.println("\n===== INVENTORY =====");

        for (Product product : repo.getAllProducts()) {
            System.out.println(
                    product.id + "  " +
                    product.name + "  Qty:" +
                    product.quantity + "  Price: ₹" +
                    product.price);
        }
    }
}

public class ShoppingInventorySystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ShoppingService service = new ShoppingService();

        while (true) {

            System.out.println("\n===== SHOPPING INVENTORY SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. Purchase Product");
            System.out.println("3. Cancel Last Order");
            System.out.println("4. Display Inventory");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    service.addProduct(new Product(id, name, qty, price));
                    break;

                case 2:

                    System.out.print("Enter Product ID: ");
                    id = sc.nextInt();

                    service.purchaseProduct(id);
                    break;

                case 3:

                    service.cancelLastOrder();
                    break;

                case 4:

                    service.displayInventory();
                    break;

                case 5:

                    System.out.println("Thank You");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice");
            }
        }
    }
}
