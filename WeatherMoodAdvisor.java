import java.util.Scanner;

public class WeatherMoodAdvisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Weather Mood Advisor");
        System.out.print("Enter weather: sunny/rainy/cloudy/cold: ");
        String weather = sc.nextLine().toLowerCase();

        switch (weather) {
            case "sunny":
                System.out.println("Suggestion: Wear sunglasses and drink water.");
                break;
            case "rainy":
                System.out.println("Suggestion: Carry an umbrella.");
                break;
            case "cloudy":
                System.out.println("Suggestion: Good day for a walk.");
                break;
            case "cold":
                System.out.println("Suggestion: Wear a jacket.");
                break;
            default:
                System.out.println("Unknown weather type.");
        }

        sc.close();
    }
}
