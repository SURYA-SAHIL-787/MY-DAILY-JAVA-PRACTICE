import java.util.*;

class Vehicle {

    int id;
    String owner;

    Vehicle(int id, String owner) {
        this.id = id;
        this.owner = owner;
    }
}

class ParkingRepository {

    HashMap<Integer, Vehicle> vehicles = new HashMap<>();

    void park(Vehicle vehicle) {
        vehicles.put(vehicle.id, vehicle);
    }

    Vehicle find(int id) {
        return vehicles.get(id);
    }

    Collection<Vehicle> getAll() {
        return vehicles.values();
    }
}

class ParkingService {

    ParkingRepository repo = new ParkingRepository();
    Stack<Vehicle> history = new Stack<>();

    void parkVehicle(Vehicle vehicle) {
        repo.park(vehicle);
        history.push(vehicle);
        System.out.println("Vehicle Parked.");
    }

    void exitLastVehicle() {

        if (history.isEmpty()) {
            System.out.println("Parking Empty.");
            return;
        }

        Vehicle vehicle = history.pop();

        System.out.println("Vehicle Exited: " + vehicle.owner);
    }

    void searchVehicle(int id) {

        Vehicle vehicle = repo.find(id);

        if (vehicle != null)
            System.out.println(vehicle.id + " " + vehicle.owner);
        else
            System.out.println("Vehicle Not Found.");
    }

    void displayVehicles() {

        for (Vehicle vehicle : repo.getAll()) {
            System.out.println(vehicle.id + " " + vehicle.owner);
        }
    }
}

public class ParkingManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ParkingService service = new ParkingService();

        while (true) {

            System.out.println("\n1.Park Vehicle");
            System.out.println("2.Exit Last Vehicle");
            System.out.println("3.Search Vehicle");
            System.out.println("4.Display Vehicles");
            System.out.println("5.Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Vehicle ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Owner Name: ");
                    String owner = sc.nextLine();

                    service.parkVehicle(new Vehicle(id, owner));
                    break;

                case 2:
                    service.exitLastVehicle();
                    break;

                case 3:
                    System.out.print("Vehicle ID: ");
                    service.searchVehicle(sc.nextInt());
                    break;

                case 4:
                    service.displayVehicles();
                    break;

                case 5:
                    return;
            }
        }
    }
}
