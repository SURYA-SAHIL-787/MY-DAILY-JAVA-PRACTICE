import java.util.ArrayList;

class FoodItem {
    private String name;
    private double price;
    private int quantity;

    FoodItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return price * quantity;
    }

    void displayItem() {
        System.out.println(
                name + " | ₹" + price +
                " x " + quantity +
                " = ₹" + getTotalPrice()
        );
    }
}

class Order {
    private String customerName;
    private ArrayList<FoodItem> items;

    Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
    }

    void addItem(FoodItem item) {
        items.add(item);
    }

    double calculateFoodTotal() {
        double total = 0;

        for (FoodItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    double calculateDeliveryCharge() {
        return calculateFoodTotal() >= 500 ? 0 : 40;
    }

    void displayBill() {
        System.out.println("Customer: " + customerName);
        System.out.println("\nOrdered Items:");

        for (FoodItem item : items) {
            item.displayItem();
        }

        double foodTotal = calculateFoodTotal();
        double deliveryCharge = calculateDeliveryCharge();

        System.out.println("\nFood Total: ₹" + foodTotal);
        System.out.println("Delivery Charge: ₹" + deliveryCharge);
        System.out.println("Final Bill: ₹" + (foodTotal + deliveryCharge));
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        Order order = new Order("Sahil");

        order.addItem(new FoodItem("Veg Burger", 120, 2));
        order.addItem(new FoodItem("French Fries", 90, 1));
        order.addItem(new FoodItem("Cold Coffee", 110, 2));

        order.displayBill();
    }
}
