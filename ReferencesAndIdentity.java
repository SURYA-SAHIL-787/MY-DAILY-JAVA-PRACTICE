/*
 * QUESTION 2: Create a Thor object and a second reference to that object.
 * Change Thor's state through the second reference. Then create another
 * object also named Thor and compare the references with ==.
 * Demonstrate that a method can change a referenced object's state, but
 * reassigning its parameter cannot replace the caller's reference.
 * Finally, check a null reference safely. Predict the output before running.
 */
public class ReferencesAndIdentity {
    public static void main(String[] args) {
        ReferenceHero thor = new ReferenceHero();
        thor.name = "Thor";
        thor.energy = 80;

        ReferenceHero alias = thor; // Copies the reference, not the object.
        alias.energy = 90;
        System.out.println("Thor energy after alias change: " + thor.energy);
        System.out.println("thor == alias: " + (thor == alias));

        ReferenceHero anotherThor = new ReferenceHero();
        anotherThor.name = "Thor";
        anotherThor.energy = 90;
        // == checks object identity for references, not matching field values.
        System.out.println("thor == anotherThor: " + (thor == anotherThor));

        trainHero(thor);
        System.out.println("Thor energy after method: " + thor.energy);
        tryToReplaceHero(thor);
        System.out.println("Caller still refers to: " + thor.name);
        System.out.println("thor == alias after method: " + (thor == alias));

        ReferenceHero missingHero = null; // No object is referenced.
        if (missingHero == null) {
            System.out.println("No hero assigned yet.");
        } else {
            System.out.println(missingHero.name);
        }
        // Accessing missingHero.name while it is null would throw
        // NullPointerException, so we check before accessing the object.
    }

    static void trainHero(ReferenceHero hero) {
        // Java passes a COPY of the reference value. Both references still
        // point to the same object, so this mutation is visible to the caller.
        hero.energy += 5;
    }

    static void tryToReplaceHero(ReferenceHero hero) {
        // Reassignment changes only this local parameter's reference.
        hero = new ReferenceHero();
        hero.name = "Superman";
        System.out.println("Inside replacement method: " + hero.name);
    }
}

class ReferenceHero {
    String name;
    int energy;
}
