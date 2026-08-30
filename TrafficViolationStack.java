import java.util.Stack;

class Violation {
    String vehicleNumber;
    String violationType;

    Violation(String vehicleNumber, String violationType) {
        this.vehicleNumber = vehicleNumber;
        this.violationType = violationType;
    }
}

public class TrafficViolationStack {

    public static void main(String[] args) {

        Stack<Violation> violations = new Stack<>();

        violations.push(
            new Violation("KA01AB1234", "Signal Jump")
        );

        violations.push(
            new Violation("KA02CD5678", "No Helmet")
        );

        violations.push(
            new Violation("KA03EF9012", "Over Speeding")
        );

        System.out.println("Traffic Violations:");

        for (Violation v : violations) {
            System.out.println(
                v.vehicleNumber + " - " + v.violationType
            );
        }

        Violation recent = violations.pop();

        System.out.println("\nLatest Violation Removed:");

        System.out.println(
            recent.vehicleNumber + " - " + recent.violationType
        );
    }
}
