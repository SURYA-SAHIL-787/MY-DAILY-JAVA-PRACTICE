/*
 * QUESTION 3: Create villain records for Loki and Joker.
 * A two-argument constructor sets the name and threat level.
 * A one-argument constructor uses a default threat level of 1.
 * Initialize the plan count to 0, increase Loki's count, and print both.
 * Use this(...) to reuse one constructor from the other.
 */
public class VillainConstructors {
    public static void main(String[] args) {
        VillainRecord loki = new VillainRecord("Loki", 8);
        VillainRecord joker = new VillainRecord("Joker");

        loki.describe();
        joker.describe();
        loki.makePlan();
        System.out.println("After Loki makes a plan:");
        loki.describe();
        joker.describe();
    }
}

class VillainRecord {
    String name;
    int threatLevel;
    int plansMade = 0; // Field initializer, run for each new object.

    // A constructor has the class name and no return type (not even void).
    VillainRecord(String name, int threatLevel) {
        this.name = name;
        this.threatLevel = threatLevel;
    }

    // Overloaded constructor: same name, different parameter list.
    VillainRecord(String name) {
        this(name, 1); // Calls the two-argument constructor; must be first here.
    }

    void makePlan() {
        this.plansMade++;
    }

    void describe() {
        System.out.println(name + " | threat: " + threatLevel
                + " | plans: " + plansMade);
    }
}
