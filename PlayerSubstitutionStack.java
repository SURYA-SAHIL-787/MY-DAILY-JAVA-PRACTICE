import java.util.*;

class Player {
    private int jerseyNumber;
    private String playerName;

    public Player(int jerseyNumber, String playerName) {
        this.jerseyNumber = jerseyNumber;
        this.playerName = playerName;
    }

    public void displayPlayer() {
        System.out.println(
            "Jersey " + jerseyNumber + " - " + playerName
        );
    }
}

public class PlayerSubstitutionStack {

    public static void main(String[] args) {

        Stack<Player> substitutedPlayers = new Stack<>();

        substitutedPlayers.push(new Player(10, "Arjun"));
        substitutedPlayers.push(new Player(7, "Rahul"));
        substitutedPlayers.push(new Player(9, "Karan"));

        System.out.println("Substituted Players:");

        for (Player player : substitutedPlayers) {
            player.displayPlayer();
        }

        System.out.println("\nReverse Substitution Order:");

        while (!substitutedPlayers.isEmpty()) {
            substitutedPlayers.pop().displayPlayer();
        }
    }
}
