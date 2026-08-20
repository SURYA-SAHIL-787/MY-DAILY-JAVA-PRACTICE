import java.util.*;

class Car {

    protected String model;
    protected double price;

    public Car(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Model: " + model);
        System.out.println("Price: Rs." + price);
    }
}

class ElectricCar extends Car {

    private int batteryCapacity;

    public ElectricCar(String model, double price, int batteryCapacity) {
        super(model, price);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("\nElectric Car");
        System.out.println("Model: " + model);
        System.out.println("Price: Rs." + price);
        System.out.println("Battery: " + batteryCapacity + " kWh");
    }
}

class PetrolCar extends Car {

    private double mileage;

    public PetrolCar(String model, double price, double mileage) {
        super(model, price);
        this.mileage = mileage;
    }

    @Override
    public void displayDetails() {
        System.out.println("\nPetrol Car");
        System.out.println("Model: " + model);
        System.out.println("Price: Rs." + price);
        System.out.println("Mileage: " + mileage + " km/l");
    }
}

public class CarInventorySystem {

    public static void main(String[] args) {

        ArrayList<Car> inventory = new ArrayList<>();

        inventory.add(
                new ElectricCar("Tata Nexon EV", 1500000, 40)
        );

        inventory.add(
                new PetrolCar("Hyundai Creta", 1200000, 17.4)
        );

        inventory.add(
                new ElectricCar("MG ZS EV", 1900000, 50)
        );

        for (Car car : inventory) {
            car.displayDetails();
        }
    }
}
