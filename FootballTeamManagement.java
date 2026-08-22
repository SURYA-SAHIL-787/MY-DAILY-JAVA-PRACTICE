import java.util.*;

class FootballPlayer {

    protected String name;
    protected int jerseyNumber;

    public FootballPlayer(String name, int jerseyNumber) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
    }

    public void displayRole() {
        System.out.println("Football Player");
    }
}

class Forward extends FootballPlayer {

    public Forward(String name, int jerseyNumber) {
        super(name, jerseyNumber);
    }

    @Override
    public void displayRole() {
        System.out.println(
            name + " #" + jerseyNumber +
            " - Forward: Responsible for scoring goals"
        );
    }
}

class Midfielder extends FootballPlayer {

    public Midfielder(String name, int jerseyNumber) {
        super(name, jerseyNumber);
    }

    @Override
    public void displayRole() {
        System.out.println(
            name + " #" + jerseyNumber +
            " - Midfielder: Controls the midfield"
        );
    }
}

class Goalkeeper extends FootballPlayer {

    public Goalkeeper(String name, int jerseyNumber) {
        super(name, jerseyNumber);
    }

    @Override
    public void displayRole() {
        System.out.println(
            name + " #" + jerseyNumber +
            " - Goalkeeper: Protects the goal"
        );
    }
}

public class FootballTeamManagement {

    public static void main(String[] args) {

        ArrayList<FootballPlayer> team = new ArrayList<>();

        team.add(new Forward("Arjun", 9));
        team.add(new Midfielder("Rahul", 8));
        team.add(new Goalkeeper("Vikram", 1));
        team.add(new Forward("Karan", 11));

        System.out.println("Football Team:");

        for (FootballPlayer player : team) {
            player.displayRole();
        }
    }
}
