import java.util.*;

class RailwayNetwork {

    private HashMap<String, ArrayList<String>> network;

    public RailwayNetwork() {
        network = new HashMap<>();
    }

    public void addStation(String station) {
        network.putIfAbsent(station, new ArrayList<>());
    }

    public void addTrack(String station1, String station2) {

        addStation(station1);
        addStation(station2);

        network.get(station1).add(station2);
        network.get(station2).add(station1);
    }

    public void displayNetwork() {

        for (String station : network.keySet()) {

            System.out.print(station + " -> ");

            for (String connectedStation : network.get(station)) {
                System.out.print(connectedStation + " ");
            }

            System.out.println();
        }
    }
}

public class RailwayRouteNetwork {

    public static void main(String[] args) {

        RailwayNetwork railway = new RailwayNetwork();

        railway.addTrack("Chennai", "Bengaluru");
        railway.addTrack("Chennai", "Vellore");
        railway.addTrack("Bengaluru", "Mysuru");
        railway.addTrack("Bengaluru", "Hubballi");
        railway.addTrack("Mysuru", "Coimbatore");

        System.out.println("Railway Route Network:\n");

        railway.displayNetwork();
    }
}
