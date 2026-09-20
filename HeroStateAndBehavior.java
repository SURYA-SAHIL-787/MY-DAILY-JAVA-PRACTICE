/*
 * QUESTION 1: Create two hero objects: Iron Man and Batman.
 * Give each a name and energy (state). Add methods to introduce the hero
 * and train (behavior). Use this to distinguish fields from parameters.
 * Train only Iron Man and show that Batman's state stays unchanged.
 * Try it yourself first; the complete runnable solution is below.
 */
public class HeroStateAndBehavior {
    public static void main(String[] args) {
        // A class is a blueprint; each new expression creates a distinct object.
        TrainingHero ironMan = new TrainingHero();
        TrainingHero batman = new TrainingHero();

        ironMan.configure("Iron Man", 50);
        batman.configure("Batman", 40);
        ironMan.introduce();
        batman.introduce();

        ironMan.train();
        System.out.println("After Iron Man trains:");
        ironMan.introduce();
        batman.introduce();
    }
}

class TrainingHero {
    // Instance fields: every TrainingHero object has its own state.
    // Question 4 will protect fields with private and validation.
    String name;
    int energy;

    void configure(String name, int energy) {
        this.name = name;     // this.name is the current object's field.
        this.energy = energy; // energy alone is the method parameter.
    }

    void train() {
        this.energy += 10;
    }

    void introduce() {
        System.out.println(this.name + " has " + this.energy + " energy.");
    }
}
