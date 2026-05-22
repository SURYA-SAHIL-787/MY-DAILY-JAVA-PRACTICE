import java.util.*;

class Vehicle {
    String number;
    String type;

    Vehicle(String number, String type) {
        this.number = number;
        this.type = type;
    }
}

class ParkingSlot {
    int slotId;
    boolean occupied;
    Vehicle vehicle;

    ParkingSlot(int slotId) {
        this.slotId = slotId;
        this.occupied = false;
        this.vehicle = null;
    }
}

class ParkingLot {
    Queue<ParkingSlot> availableSlots = new LinkedList<>();
    HashMap<String, ParkingSlot> parkedVehicles = new HashMap<>();

    ParkingLot(int totalSlots) {
        for (int i = 1; i <= totalSlots; i++) {
            availableSlots.add(new ParkingSlot(i));
        }
    }

    void parkVehicle(Vehicle vehicle) {
        if (availableSlots.isEmpty()) {
            System.out.println("Parking lot is full");
            return;
        }

        ParkingSlot slot = availableSlots.poll();
        slot.occupied = true;
        slot.vehicle = vehicle;
        parkedVehicles.put(vehicle.number, slot);

        System.out.println(vehicle.number + " parked at slot " + slot.slotId);
    }

    void removeVehicle(String vehicleNumber) {
        if (!parkedVehicles.containsKey(vehicleNumber)) {
            System.out.println("Vehicle not found");
            return;
        }

        ParkingSlot slot = parkedVehicles.get(vehicleNumber);
        parkedVehicles.remove(vehicleNumber);

        System.out.println(vehicleNumber + " removed from slot " + slot.slotId);

        slot.occupied = false;
        slot.vehicle = null;
        availableSlots.add(slot);
    }

    void searchVehicle(String vehicleNumber) {
        if (!parkedVehicles.containsKey(vehicleNumber)) {
            System.out.println("Vehicle not found");
            return;
        }

        ParkingSlot slot = parkedVehicles.get(vehicleNumber);
        System.out.println(vehicleNumber + " is parked at slot " + slot.slotId);
    }

    void showParkedVehicles() {
        for (String number : parkedVehicles.keySet()) {
            ParkingSlot slot = parkedVehicles.get(number);
            System.out.println(number + " | Slot: " + slot.slotId + " | Type: " + slot.vehicle.type);
        }
    }
}

public class SmartParkingLotSystem {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(3);

        lot.parkVehicle(new Vehicle("AP01AB1234", "Car"));
        lot.parkVehicle(new Vehicle("TS09CD5678", "Bike"));
        lot.parkVehicle(new Vehicle("KA05EF9999", "Car"));
        lot.parkVehicle(new Vehicle("TN10GH1111", "Van"));

        lot.showParkedVehicles();

        lot.searchVehicle("TS09CD5678");

        lot.removeVehicle("AP01AB1234");

        lot.parkVehicle(new Vehicle("MH12KL2222", "Truck"));

        lot.showParkedVehicles();
    }
}
