import java.util.Scanner;

public class VirtualPetGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hunger = 50;
        int happiness = 50;
        int energy = 50;

        while (true) {
            System.out.println("\nVirtual Pet Game");
            System.out.println("Hunger: " + hunger);
            System.out.println("Happiness: " + happiness);
            System.out.println("Energy: " + energy);

            System.out.println("\n1. Feed Pet");
            System.out.println("2. Play with Pet");
            System.out.println("3. Let Pet Sleep");
            System.out.println("4. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                hunger -= 10;
                happiness += 5;
                System.out.println("You fed your pet.");
            } else if (choice == 2) {
                happiness += 10;
                energy -= 10;
                hunger += 5;
                System.out.println("You played with your pet.");
            } else if (choice == 3) {
                energy += 15;
                hunger += 5;
                System.out.println("Your pet slept.");
            } else if (choice == 4) {
                System.out.println("Game ended.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }

            if (hunger < 0) hunger = 0;
            if (happiness > 100) happiness = 100;
            if (energy > 100) energy = 100;
        }

        sc.close();
    }
}


