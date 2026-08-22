import java.util.*;

class GoalScorer {

    private int jerseyNumber;
    private String name;
    private int goals;

    public GoalScorer(int jerseyNumber, String name, int goals) {
        this.jerseyNumber = jerseyNumber;
        this.name = name;
        this.goals = goals;
    }

    public void displayDetails() {
        System.out.println("Jersey Number: " + jerseyNumber);
        System.out.println("Player Name: " + name);
        System.out.println("Goals Scored: " + goals);
    }
}

public class FootballGoalTracker {

    public static void main(String[] args) {

        HashMap<Integer, GoalScorer> players = new HashMap<>();

        players.put(
            7,
            new GoalScorer(7, "Rahul", 5)
        );

        players.put(
            9,
            new GoalScorer(9, "Arjun", 8)
        );

        players.put(
            10,
            new GoalScorer(10, "Karan", 6)
        );

        players.put(
            11,
            new GoalScorer(11, "Vijay", 4)
        );

        int searchJersey = 9;

        System.out.println(
            "Searching for Jersey Number: " + searchJersey
        );

        if (players.containsKey(searchJersey)) {

            System.out.println("\nPlayer Found:");
            players.get(searchJersey).displayDetails();

        } else {

            System.out.println("Player Not Found");
        }
    }
}
