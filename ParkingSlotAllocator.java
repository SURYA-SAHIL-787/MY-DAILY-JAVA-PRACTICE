import java.util.*;

class Vehicle {
    private final String number;

    Vehicle(String number) {
        this.number = number;
    }

    String getNumber() {
        return number;
    }
}

class ParkingLot {
    private final PriorityQueue<Integer> freeSlots =
            new PriorityQueue<>();

    private final Map<String, Integer> parkedVehicles =
            new HashMap<>();

    ParkingLot(int totalSlots) {
        for (int slot = 1; slot <= totalSlots; slot++) {
            freeSlots.offer(slot);
        }
    }

    void park(Vehicle vehicle) {
        if (freeSlots.isEmpty()) {
            System.out.println("Parking full");
            return;
        }

        if (parkedVehicles.containsKey(vehicle.getNumber())) {
            System.out.println("Vehicle already parked");
            return;
        }

        int slot = freeSlots.poll();
        parkedVehicles.put(vehicle.getNumber(), slot);

        System.out.println(
                vehicle.getNumber() + " parked at slot " + slot
        );
    }

    void remove(String vehicleNumber) {
        Integer slot = parkedVehicles.remove(vehicleNumber);

        if (slot == null) {
            System.out.println("Vehicle not found");
            return;
        }

        freeSlots.offer(slot);
        System.out.println("Slot " + slot + " is now free");
    }

    void findVehicle(String vehicleNumber) {
        System.out.println(
                "Slot: "
                + parkedVehicles.getOrDefault(vehicleNumber, -1)
        );
    }
}

public class ParkingSlotAllocator {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(3);

        lot.park(new Vehicle("KA01AB1234"));
        lot.park(new Vehicle("KA02CD5678"));

        lot.remove("KA01AB1234");
        lot.park(new Vehicle("KA03EF9999"));

        lot.findVehicle("KA03EF9999");
    }
}
