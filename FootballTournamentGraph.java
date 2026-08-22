import java.util.*;

class Tournament {

    private HashMap<String, ArrayList<String>> graph;

    public Tournament() {
        graph = new HashMap<>();
    }

    public void addTeam(String team) {
        graph.putIfAbsent(team, new ArrayList<>());
    }

    public void addMatch(String team1, String team2) {

        addTeam(team1);
        addTeam(team2);

        graph.get(team1).add(team2);
        graph.get(team2).add(team1);
    }

    public void displayMatches() {

        for (String team : graph.keySet()) {

            System.out.print(team + " -> ");

            for (String opponent : graph.get(team)) {
                System.out.print(opponent + " ");
            }

            System.out.println();
        }
    }
}

public class FootballTournamentGraph {

    public static void main(String[] args) {

        Tournament tournament = new Tournament();

        tournament.addMatch("Team A", "Team B");
        tournament.addMatch("Team A", "Team C");
        tournament.addMatch("Team B", "Team D");
        tournament.addMatch("Team C", "Team D");
        tournament.addMatch("Team C", "Team E");

        System.out.println("Football Tournament Match Network:\n");

        tournament.displayMatches();
    }
}
