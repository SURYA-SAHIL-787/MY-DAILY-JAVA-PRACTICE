
import java.time.LocalDate;
import java.util.*;

class Food {
    private String name;
    private LocalDate expiryDate;

    public Food(String name, LocalDate expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}

public class FoodExpiryManager {
    public static void main(String[] args) {

        PriorityQueue<Food> foods = new PriorityQueue<>(
            Comparator.comparing(Food::getExpiryDate)
        );

        LocalDate today = LocalDate.now();

        foods.add(new Food("Milk", today.plusDays(2)));
        foods.add(new Food("Bread", today.plusDays(1)));
        foods.add(new Food("Cheese", today.plusDays(7)));
        foods.add(new Food("Yogurt", today.minusDays(1)));

        while (!foods.isEmpty()) {
            Food food = foods.poll();

            if (food.getExpiryDate().isBefore(today)) {
                System.out.println(food.getName() + " - EXPIRED");
            } else if (!food.getExpiryDate().isAfter(today.plusDays(3))) {
                System.out.println(food.getName() + " - USE FIRST");
            } else {
                System.out.println(food.getName() + " - FRESH");
            }
        }
    }
}
