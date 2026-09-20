/*
 * QUESTION 4: Protect Captain America's and Wonder Woman's energy.
 * Keep name and energy private. Require a nonblank name and energy 0..100.
 * Provide getters, usePower(cost), and recharge(amount), instead of allowing
 * direct field changes. Reject nonpositive amounts. If energy is too low,
 * return false without changing it. Recharging must never exceed 100.
 * Demonstrate success, insufficient energy, capping, and invalid input.
 */
public class EncapsulatedHeroEnergy {
    public static void main(String[] args) {
        EnergyHero captain = new EnergyHero("Captain America", 60);
        EnergyHero wonderWoman = new EnergyHero("Wonder Woman", 90);

        System.out.println(captain.getName() + " starts with " + captain.getEnergy());
        System.out.println("Use power costing 25: " + captain.usePower(25));
        System.out.println("Energy now: " + captain.getEnergy());
        System.out.println("Use power costing 50: " + captain.usePower(50));
        System.out.println("Energy still: " + captain.getEnergy());
        captain.recharge(80);
        System.out.println("Energy after recharge: " + captain.getEnergy());
        System.out.println(wonderWoman.getName() + " has " + wonderWoman.getEnergy());

        // Catch this deliberate error so the sample finishes normally.
        try {
            captain.recharge(-10);
        } catch (IllegalArgumentException error) {
            System.out.println("Invalid recharge: " + error.getMessage());
        }
        // captain.energy = 500; // Would not compile: energy is private.
    }
}

class EnergyHero {
    private final String name; // Set once by the constructor.
    private int energy;

    public EnergyHero(String name, int energy) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be blank.");
        }
        if (energy < 0 || energy > 100) {
            throw new IllegalArgumentException("Energy must be from 0 to 100.");
        }
        this.name = name.trim();
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean usePower(int cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Cost must be positive.");
        }
        if (cost > energy) {
            return false; // A failed action leaves state unchanged.
        }
        energy -= cost;
        return true;
    }

    public void recharge(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        // Add only the available space, keeping energy within 0..100.
        energy += Math.min(amount, 100 - energy);
    }
}
