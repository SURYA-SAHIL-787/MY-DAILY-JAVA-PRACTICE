import java.util.*;

class BusNetwork {

    private HashMap<String, ArrayList<String>> network;

    public BusNetwork() {
        network = new HashMap<>();
    }

    public void addStation(String station) {
        network.putIfAbsent(station, new ArrayList<>());
    }

    public void addRoute(String station1, String station2) {

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

public class RTCBusRouteGraph {

    public static void main(String[] args) {

        BusNetwork network = new BusNetwork();

        network.addRoute("Miyapur", "Kukatpally");
        network.addRoute("Kukatpally", "Ameerpet");
        network.addRoute("Ameerpet", "Lakdikapul");
        network.addRoute("Lakdikapul", "Mehdipatnam");
        network.addRoute("Ameerpet", "Secunderabad");

        System.out.println("RTC Bus Route Network:\n");

        network.displayNetwork();
    }
}
