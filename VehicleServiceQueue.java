import java.util.*;

class Vehicle {
    private String registrationNumber;
    private String model;
    private String serviceType;

    public Vehicle(String registrationNumber, String model, String serviceType) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.serviceType = serviceType;
    }

    public void displayDetails() {
        System.out.println(
                registrationNumber + " - " +
                model + " - " +
                serviceType
        );
    }
}

public class VehicleServiceQueue {
    public static void main(String[] args) {

        Queue<Vehicle> serviceQueue = new LinkedList<>();

        serviceQueue.add(new Vehicle("TN01AB1234", "Hyundai Creta", "Oil Change"));
        serviceQueue.add(new Vehicle("TN02CD5678", "Tata Nexon", "Brake Service"));
        serviceQueue.add(new Vehicle("TN03EF9012", "Mahindra XUV700", "General Service"));

        System.out.println("Vehicles waiting for service:");

        for (Vehicle vehicle : serviceQueue) {
            vehicle.displayDetails();
        }

        System.out.println("\nServicing vehicles:");

        while (!serviceQueue.isEmpty()) {
            Vehicle vehicle = serviceQueue.poll();
            vehicle.displayDetails();
        }
    }
}
