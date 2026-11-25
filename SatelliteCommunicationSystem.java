import java.util.*;

enum SignalType {
    INTERNET, WEATHER, NAVIGATION
}

interface SignalReceiver {
    boolean supports(SignalType type);
    void transmit(String message);
}

abstract class Satellite implements SignalReceiver {
    private final String satelliteId;
    private boolean operational = true;

    Satellite(String satelliteId) {
        this.satelliteId = satelliteId;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public boolean isOperational() {
        return operational;
    }

    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    public abstract boolean supports(SignalType type);

    public abstract void transmit(String message);
}

class InternetSatellite extends Satellite {
    InternetSatellite(String id) {
        super(id);
    }

    @Override
    public boolean supports(SignalType type) {
        return type == SignalType.INTERNET;
    }

    @Override
    public void transmit(String message) {
        System.out.println(getSatelliteId()
                + " transmitting internet data: " + message);
    }
}

class WeatherSatellite extends Satellite {
    WeatherSatellite(String id) {
        super(id);
    }

    @Override
    public boolean supports(SignalType type) {
        return type == SignalType.WEATHER;
    }

    @Override
    public void transmit(String message) {
        System.out.println(getSatelliteId()
                + " transmitting weather data: " + message);
    }
}

class NavigationSatellite extends Satellite {
    NavigationSatellite(String id) {
        super(id);
    }

    @Override
    public boolean supports(SignalType type) {
        return type == SignalType.NAVIGATION;
    }

    @Override
    public void transmit(String message) {
        System.out.println(getSatelliteId()
                + " transmitting navigation data: " + message);
    }
}

class CommunicationRequest {
    private final SignalType type;
    private final String message;

    CommunicationRequest(SignalType type, String message) {
        this.type = type;
        this.message = message;
    }

    public SignalType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}

class GroundStation {
    private final List<Satellite> satellites = new ArrayList<>();

    public void registerSatellite(Satellite satellite) {
        satellites.add(satellite);
    }

    public void processRequest(CommunicationRequest request) {
        for (Satellite satellite : satellites) {
            if (satellite.isOperational()
                    && satellite.supports(request.getType())) {

                satellite.transmit(request.getMessage());
                return;
            }
        }

        System.out.println("No compatible satellite available");
    }
}

public class SatelliteCommunicationSystem {
    public static void main(String[] args) {
        GroundStation station = new GroundStation();

        Satellite internet = new InternetSatellite("SAT-101");
        Satellite weather = new WeatherSatellite("SAT-102");
        Satellite navigation = new NavigationSatellite("SAT-103");

        station.registerSatellite(internet);
        station.registerSatellite(weather);
        station.registerSatellite(navigation);

        station.processRequest(new CommunicationRequest(
                SignalType.INTERNET, "Network packet"));

        station.processRequest(new CommunicationRequest(
                SignalType.WEATHER, "Rain forecast"));

        station.processRequest(new CommunicationRequest(
                SignalType.NAVIGATION, "Location coordinates"));

        internet.setOperational(false);

        station.processRequest(new CommunicationRequest(
                SignalType.INTERNET, "Network packet"));
    }
}
