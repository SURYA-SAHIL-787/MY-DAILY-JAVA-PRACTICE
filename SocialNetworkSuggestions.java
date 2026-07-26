import java.util.*;

class SocialNetwork {
    private final Map<String, Set<String>> graph =
            new HashMap<>();

    void addUser(String user) {
        graph.putIfAbsent(user, new HashSet<>());
    }

    void addFriendship(String first, String second) {
        addUser(first);
        addUser(second);

        graph.get(first).add(second);
        graph.get(second).add(first);
    }

    Set<String> suggestFriends(String user) {
        Set<String> suggestions = new TreeSet<>();

        if (!graph.containsKey(user)) {
            return suggestions;
        }

        Set<String> directFriends = graph.get(user);

        for (String friend : directFriends) {
            for (String candidate : graph.get(friend)) {
                if (!candidate.equals(user)
                        && !directFriends.contains(candidate)) {
                    suggestions.add(candidate);
                }
            }
        }

        return suggestions;
    }

    void printNetwork() {
        for (String user : new TreeSet<>(graph.keySet())) {
            System.out.println(user + " -> " + graph.get(user));
        }
    }
}

public class SocialNetworkSuggestions {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();

        network.addFriendship("Aman", "Bina");
        network.addFriendship("Aman", "Charan");
        network.addFriendship("Bina", "Divya");
        network.addFriendship("Charan", "Esha");
        network.addFriendship("Divya", "Farhan");

        network.printNetwork();

        System.out.println(
                "Suggestions for Aman: "
                + network.suggestFriends("Aman")
        );
    }
}
