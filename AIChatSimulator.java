import java.util.Scanner;

public class AIChatSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("AI Chat Simulator");
        System.out.println("Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String msg = sc.nextLine().toLowerCase();

            if (msg.equals("bye")) {
                System.out.println("Bot: Goodbye!");
                break;
            } else if (msg.contains("hello") || msg.contains("hi")) {
                System.out.println("Bot: Hello! How are you?");
            } else if (msg.contains("sad")) {
                System.out.println("Bot: I hope your day gets better.");
            } else if (msg.contains("happy")) {
                System.out.println("Bot: That's great!");
            } else if (msg.contains("name")) {
                System.out.println("Bot: I am MiniBot.");
            } else {
                System.out.println("Bot: Interesting! Tell me more.");
            }
        }

        sc.close();
    }
}
