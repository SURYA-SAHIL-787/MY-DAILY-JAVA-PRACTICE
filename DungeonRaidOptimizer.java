import java.util.*;

// 1. Item Class
class Item {
    private String name;
    private int weight;
    private int power;
    private String type;

    public Item(String name, int weight, int power, String type) {
        this.name = name;
        this.weight = weight;
        this.power = power;
        this.type = type;
    }

    public String getName() { return name; }
    public int getWeight() { return weight; }
    public int getPower() { return power; }
    public String getType() { return type; }
}

// 2. DungeonLoot Class
class DungeonLoot {
    private List<Item> inventory;
    private Map<String, List<String>> conflicts;

    DungeonLoot(List<Item> inventory, Map<String, List<String>> conflicts) {
        this.inventory = inventory;
        this.conflicts = conflicts != null ? conflicts : new HashMap<>();
    }

    public List<Item> getInventory() { return inventory; }
    public Map<String, List<String>> getConflicts() { return conflicts; }
}

// 3. RaidOptimizer Class
class RaidOptimizer {
    private int maxPower = 0;
    private List<Item> bestLoadout = new ArrayList<>();

    public List<Item> getBestLoadout(List<Item> inventory, int capacity, Map<String, List<String>> conflicts) {
        maxPower = 0;
        bestLoadout.clear();
        
        // Sort items by power-to-weight ratio to optimize backtracking pruning
        List<Item> sortedItems = new ArrayList<>(inventory);
        sortedItems.sort((a, b) -> Double.compare((double)b.getPower() / b.getWeight(), (double)a.getPower() / a.getWeight()));

        backtrack(sortedItems, 0, 0, 0, new ArrayList<>(), new HashSet<>(), capacity, conflicts);
        return bestLoadout;
    }

    private void backtrack(List<Item> items, int index, int currentWeight, int currentPower, 
                           List<Item> currentLoadout, Set<String> activeTypes, 
                           int capacity, Map<String, List<String>> conflicts) {
        if (currentWeight > capacity) return;

        if (currentPower > maxPower) {
            maxPower = currentPower;
            bestLoadout = new ArrayList<>(currentLoadout);
        }

        for (int i = index; i < items.size(); i++) {
            Item item = items.get(i);
            
            // Check conflict rules against active item types
            boolean hasConflict = false;
            if (conflicts.containsKey(item.getType())) {
                for (String restrictedType : conflicts.get(item.getType())) {
                    if (activeTypes.contains(restrictedType)) {
                        hasConflict = true;
                        break;
                    }
                }
            }

            if (!hasConflict && currentWeight + item.getWeight() <= capacity) {
                currentLoadout.add(item);
                activeTypes.add(item.getType());

                backtrack(items, i + 1, currentWeight + item.getWeight(), currentPower + item.getPower(), 
                          currentLoadout, activeTypes, capacity, conflicts);

                activeTypes.remove(item.getType());
                currentLoadout.remove(currentLoadout.size() - 1);
            }
        }
    }
}

// Execution Runner
public class DungeonRaidOptimizer {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
            new Item("Excalibur", 10, 100, "Weapon"),
            new Item("DragonShield", 15, 80, "Armor"),
            new Item("MagicStaff", 8, 90, "Weapon"),
            new Item("HealthPotion", 2, 20, "Consumable")
        );

        Map<String, List<String>> conflicts = new HashMap<>();
        // Weapons conflict with each other for demonstration or custom rule mapping
        conflicts.put("Weapon", Arrays.asList("Staff"));

        RaidOptimizer optimizer = new RaidOptimizer();
        List<Item> optimalLoadout = optimizer.getBestLoadout(items, 20, conflicts);

        System.out.println("Optimal Dungeon Raid Loadout:");
        int totalPower = 0;
        for (Item item : optimalLoadout) {
            System.out.println("- " + item.getName() + " (Power: " + item.getPower() + ", Weight: " + item.getWeight() + ")");
            totalPower += item.getPower();
        }
        System.out.println("Total Loadout Power: " + totalPower);
    }
}
