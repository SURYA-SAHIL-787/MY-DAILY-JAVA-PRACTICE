import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;

        System.out.println("Mini Quiz Game");

        System.out.println("\n1. Which language runs on JVM?");
        System.out.println("a) Python  b) Java  c) C++");
        System.out.print("Answer: ");
        if (sc.nextLine().equalsIgnoreCase("b")) score++;

        System.out.println("\n2. What is 5 + 7?");
        System.out.println("a) 10  b) 11  c) 12");
        System.out.print("Answer: ");
        if (sc.nextLine().equalsIgnoreCase("c")) score++;

        System.out.println("\n3. Which keyword creates a class?");
        System.out.println("a) class  b) create  c) new");
        System.out.print("Answer: ");
        if (sc.nextLine().equalsIgnoreCase("a")) score++;

        System.out.println("\nYour score: " + score + "/3");

        sc.close();
    }
}
