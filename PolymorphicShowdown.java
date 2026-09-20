/*
 * QUESTION 5: Model an Avengers + DC showdown with an abstract base class.
 * Give every character a name, a shared introduction method, and an
 * abstract act() method. Create IronMan, Superman, Loki, and Joker subclasses
 * that override act(). Store all four in a BattleCharacter array and call
 * the same methods on each to demonstrate polymorphism.
 */
public class PolymorphicShowdown {
    public static void main(String[] args) {
        // A base-class reference can hold an object of any of its subclasses.
        BattleCharacter[] characters = {
            new IronMan(), new Superman(), new Loki(), new Joker()
        };

        for (BattleCharacter character : characters) {
            character.introduce(); // Shared inherited behavior.
            character.act(); // Actual object's class determines the method used.
        }

        // new BattleCharacter("Someone"); // Illegal: the class is abstract.
        // No casts or character-name checks are needed to select an action.
    }
}

abstract class BattleCharacter {
    private final String name;

    protected BattleCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void introduce() {
        System.out.println("Character: " + name);
    }

    // Abstraction specifies required behavior without implementing it here.
    public abstract void act();
}

class IronMan extends BattleCharacter {
    public IronMan() {
        super("Iron Man"); // Initialize the inherited part of this object.
    }

    @Override // Lets the compiler check that this overrides a parent method.
    public void act() {
        System.out.println(getName() + " fires repulsor beams.");
    }
}

class Superman extends BattleCharacter {
    public Superman() {
        super("Superman");
    }

    @Override
    public void act() {
        System.out.println(getName() + " uses heat vision.");
    }
}

class Loki extends BattleCharacter {
    public Loki() {
        super("Loki");
    }

    @Override
    public void act() {
        System.out.println(getName() + " creates an illusion.");
    }
}

class Joker extends BattleCharacter {
    public Joker() {
        super("Joker");
    }

    @Override
    public void act() {
        System.out.println(getName() + " sets a trick trap.");
    }
}
