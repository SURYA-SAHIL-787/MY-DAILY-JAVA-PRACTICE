
import java.util.*;

class ParkingLot {
    private TreeSet<Integer> availableSlots = new TreeSet<>();

    public ParkingLot(int totalSlots) {
        for (int i = 1; i <= totalSlots; i++) {
            availableSlots.add(i);
        }
    }

    public void park(int preferredSlot) {
        Integer left = availableSlots.floor(preferredSlot);
        Integer right = availableSlots.ceiling(preferredSlot);

        if (left == null && right == null) {
            System.out.println("Parking Full");
            return;
        }

        int selected;

        if (left == null) selected = right;
        else if (right == null) selected = left;
        else {
            selected = preferredSlot - left <= right - preferredSlot
                ? left : right;
        }

        availableSlots.remove(selected);
        System.out.println("Allocated Slot: " + selected);
    }

    public void release(int slot) {
        availableSlots.add(slot);
    }
}

public class SmartParking {
    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(10);

        lot.park(5);
        lot.park(5);
        lot.park(8);

        lot.release(5);
        lot.park(5);
    }
}
