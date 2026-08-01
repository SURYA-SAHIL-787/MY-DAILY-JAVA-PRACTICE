import java.util.*;

class Order {
    int id;
    String customer;

    Order(int id, String customer) {
        this.id = id;
        this.customer = customer;
    }
}

class OrderRepository {
    HashMap<Integer, Order> completed = new HashMap<>();

    void save(Order order) {
        completed.put(order.id, order);
    }

    Order find(int id) {
        return completed.get(id);
    }
}

class DeliveryService {
    Queue<Order> pending = new LinkedList<>();
    OrderRepository repo = new OrderRepository();

    void placeOrder(Order order) {
        pending.offer(order);
        System.out.println("Order Placed Successfully.");
    }

    void deliverOrder() {
        if (pending.isEmpty()) {
            System.out.println("No Pending Orders.");
            return;
        }

        Order order = pending.poll();
        repo.save(order);
        System.out.println("Delivered to " + order.customer);
    }

    void searchOrder(int id) {
        Order order = repo.find(id);

        if (order != null) {
            System.out.println("Completed Order");
            System.out.println("Order ID : " + order.id);
            System.out.println("Customer : " + order.customer);
        } else {
            System.out.println("Order Not Found.");
        }
    }

    void displayPending() {
        if (pending.isEmpty()) {
            System.out.println("No Pending Orders.");
            return;
        }

        System.out.println("\nPending Orders");
        for (Order order : pending) {
            System.out.println(order.id + " - " + order.customer);
        }
    }
}

public class NewFoodDeliverySystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DeliveryService service = new DeliveryService();

        while (true) {

            System.out.println("\n===== FOOD DELIVERY SYSTEM =====");
            System.out.println("1. Place Order");
            System.out.println("2. Deliver Next Order");
            System.out.println("3. Search Completed Order");
            System.out.println("4. Display Pending Orders");
            System.out.println("5. Exit");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Order ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Customer Name : ");
                    String name = sc.nextLine();

                    service.placeOrder(new Order(id, name));
                    break;

                case 2:
                    service.deliverOrder();
                    break;

                case 3:
                    System.out.print("Enter Order ID : ");
                    id = sc.nextInt();
                    service.searchOrder(id);
                    break;

                case 4:
                    service.displayPending();
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
