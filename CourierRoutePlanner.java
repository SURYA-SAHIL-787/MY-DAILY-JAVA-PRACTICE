import java.util.*;

class DeliveryMap {
    private final Map<String, List<String>> roads =
            new HashMap<>();

    void addLocation(String location) {
        roads.putIfAbsent(location, new ArrayList<>());
    }

    void connect(String first, String second) {
        addLocation(first);
        addLocation(second);

        roads.get(first).add(second);
        roads.get(second).add(first);
    }

    List<String> shortestRoute(
            String start,
            String destination
    ) {
        if (!roads.containsKey(start)
                || !roads.containsKey(destination)) {
            return Collections.emptyList();
        }

        Queue<String> queue = new ArrayDeque<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(destination)) {
                break;
            }

            for (String next : roads.get(current)) {
                if (visited.add(next)) {
                    previous.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.contains(destination)) {
            return Collections.emptyList();
        }

        LinkedList<String> route = new LinkedList<>();

        for (String location = destination;
             location != null;
             location = previous.get(location)) {
            route.addFirst(location);
        }

        return route;
    }
}

public class CourierRoutePlanner {
    public static void main(String[] args) {
        DeliveryMap map = new DeliveryMap();

        map.connect("Warehouse", "Market");
        map.connect("Warehouse", "Station");
        map.connect("Market", "Hospital");
        map.connect("Station", "College");
        map.connect("College", "Hospital");

        List<String> route =
                map.shortestRoute("Warehouse", "Hospital");

        System.out.println(
                route.isEmpty()
                        ? "No route found"
                        : "Shortest route: "
                        + String.join(" -> ", route)
        );
    }
}
