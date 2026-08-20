import java.util.*;

class SparePart {

    private int partId;
    private String partName;
    private double price;

    public SparePart(int partId, String partName, double price) {
        this.partId = partId;
        this.partName = partName;
        this.price = price;
    }

    public void displayPart() {
        System.out.println("Part ID: " + partId);
        System.out.println("Part Name: " + partName);
        System.out.println("Price: Rs." + price);
    }
}

public class SparePartsInventory {

    public static void main(String[] args) {

        HashMap<Integer, SparePart> inventory = new HashMap<>();

        inventory.put(
                101,
                new SparePart(101, "Brake Pad", 2500)
        );

        inventory.put(
                102,
                new SparePart(102, "Air Filter", 1200)
        );

        inventory.put(
                103,
                new SparePart(103, "Clutch Plate", 4500)
        );

        inventory.put(
                104,
                new SparePart(104, "Spark Plug", 800)
        );

        int searchId = 103;

        System.out.println("Searching for Part ID: " + searchId);

        if (inventory.containsKey(searchId)) {

            System.out.println("\nPart Found:");

            inventory.get(searchId).displayPart();

        } else {

            System.out.println("Part not found.");
        }
    }
}
