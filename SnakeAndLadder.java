import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadder {

    static Map<Integer, Integer> snakes = new HashMap<>();
    static Map<Integer, Integer> ladders = new HashMap<>();

    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    static {
        // Snakes: head -> tail
        snakes.put(99, 54);
        snakes.put(95, 75);
        snakes.put(92, 88);
        snakes.put(47, 26);
        snakes.put(25, 7);

        // Ladders: bottom -> top
        ladders.put(2, 23);
        ladders.put(8, 34);
        ladders.put(20, 77);
        ladders.put(32, 68);
        ladders.put(41, 79);
    }

    static int rollDice() {
        return random.nextInt(6) + 1;
    }

    static int movePlayer(int position) {
        int dice = rollDice();

        System.out.println("Dice rolled: " + dice);

        if (position + dice <= 100) {
            position += dice;
        }

        // Check ladder
        if (ladders.containsKey(position)) {
            System.out.println(
                "Ladder! " + position + " -> " + ladders.get(position)
            );

            position = ladders.get(position);
        }

        // Check snake
        else if (snakes.containsKey(position)) {
            System.out.println(
                "Snake! " + position + " -> " + snakes.get(position)
            );

            position = snakes.get(position);
        }

        return position;
    }

    public static void main(String[] args) {

        int player1 = 0;
        int player2 = 0;

        System.out.println("=== SNAKE AND LADDER GAME ===");

        while (true) {

            // Player 1
            System.out.println("\nPlayer 1: Press Enter to roll dice...");
            scanner.nextLine();

            player1 = movePlayer(player1);

            System.out.println("Player 1 position: " + player1);

            if (player1 == 100) {
                System.out.println("\nPlayer 1 wins!");
                break;
            }

            // Player 2
            System.out.println("\nPlayer 2: Press Enter to roll dice...");
            scanner.nextLine();

            player2 = movePlayer(player2);

            System.out.println("Player 2 position: " + player2);

            if (player2 == 100) {
                System.out.println("\nPlayer 2 wins!");
                break;
            }
        }

        scanner.close();
    }
}
