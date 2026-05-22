import java.util.*;

class Road {
    String name;
    Queue<String> vehicles = new LinkedList<>();

    Road(String name) {
        this.name = name;
    }

    void addVehicle(String vehicleNumber) {
        vehicles.add(vehicleNumber);
    }

    void passVehicles(int count) {
        System.out.println(name + " signal is GREEN");

        for (int i = 0; i < count; i++) {
            if (!vehicles.isEmpty()) {
                System.out.println("Vehicle passed: " + vehicles.poll());
            }
        }

        System.out.println(name + " signal is RED");
    }

    int vehicleCount() {
        return vehicles.size();
    }
}

class TrafficController {
    ArrayList<Road> roads = new ArrayList<>();

    void addRoad(Road road) {
        roads.add(road);
    }

    void controlTraffic() {
        for (Road road : roads) {
            road.passVehicles(2);
        }
    }

    void showTrafficDensity() {
        for (Road road : roads) {
            System.out.println(road.name + " has " + road.vehicleCount() + " vehicles waiting");
        }
    }

    Road getBusiestRoad() {
        Road busiest = roads.get(0);

        for (Road road : roads) {
            if (road.vehicleCount() > busiest.vehicleCount()) {
                busiest = road;
            }
        }

        return busiest;
    }
}

public class TrafficSignalController {
    public static void main(String[] args) {
        Road north = new Road("North Road");
        Road south = new Road("South Road");
        Road east = new Road("East Road");

        north.addVehicle("AP01A1234");
        north.addVehicle("AP02B4567");
        north.addVehicle("AP03C1111");

        south.addVehicle("TS09X9999");

        east.addVehicle("KA05M5555");
        east.addVehicle("TN10Z7777");

        TrafficController controller = new TrafficController();

        controller.addRoad(north);
        controller.addRoad(south);
        controller.addRoad(east);

        controller.showTrafficDensity();

        Road busiest = controller.getBusiestRoad();
        System.out.println("Busiest road: " + busiest.name);

        controller.controlTraffic();

        controller.showTrafficDensity();
    }
}
