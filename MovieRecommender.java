import java.util.Scanner;
import java.util.Random;

public class MovieRecommender {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[] action = {"Avengers", "John Wick", "Mad Max"};
        String[] comedy = {"Home Alone", "The Mask", "Mr Bean"};
        String[] sciFi = {"Interstellar", "Inception", "The Matrix"};

        System.out.println("Choose genre:");
        System.out.println("1. Action");
        System.out.println("2. Comedy");
        System.out.println("3. Sci-Fi");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Recommended: " + action[rand.nextInt(action.length)]);
        } else if (choice == 2) {
            System.out.println("Recommended: " + comedy[rand.nextInt(comedy.length)]);
        } else if (choice == 3) {
            System.out.println("Recommended: " + sciFi[rand.nextInt(sciFi.length)]);
        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
