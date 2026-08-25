import java.util.*;

class Coach {

    protected String coachNumber;
    protected int capacity;

    public Coach(String coachNumber, int capacity) {
        this.coachNumber = coachNumber;
        this.capacity = capacity;
    }

    public void displayDetails() {
        System.out.println("Vande Bharat Coach");
    }
}

class ChairCar extends Coach {

    public ChairCar(String coachNumber, int capacity) {
        super(coachNumber, capacity);
    }

    @Override
    public void displayDetails() {
        System.out.println(
            coachNumber +
            " | Chair Car | Capacity: " +
            capacity
        );
    }
}

class ExecutiveChairCar extends Coach {

    public ExecutiveChairCar(String coachNumber, int capacity) {
        super(coachNumber, capacity);
    }

    @Override
    public void displayDetails() {
        System.out.println(
            coachNumber +
            " | Executive Chair Car | Capacity: " +
            capacity
        );
    }
}

public class VandeBharatCoachManagement {

    public static void main(String[] args) {

        ArrayList<Coach> coaches = new ArrayList<>();

        coaches.add(new ChairCar("C1", 78));
        coaches.add(new ChairCar("C2", 78));
        coaches.add(new ExecutiveChairCar("E1", 52));

        System.out.println("Vande Bharat Coach Details:");

        for (Coach coach : coaches) {
            coach.displayDetails();
        }
    }
}
