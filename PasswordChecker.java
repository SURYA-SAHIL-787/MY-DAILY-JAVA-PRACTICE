import java.util.Scanner;

public class PasswordChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[^a-zA-Z0-9].*")) score++;

        System.out.println("Password Score: " + score + "/5");

        if (score <= 2) {
            System.out.println("Strength: Weak");
        } else if (score <= 4) {
            System.out.println("Strength: Medium");
        } else {
            System.out.println("Strength: Strong");
        }

        sc.close();
    }
}
