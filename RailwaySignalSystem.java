import java.util.*;

class Signal {

    public void instruction() {
        System.out.println("General Railway Signal");
    }
}

class RedSignal extends Signal {

    @Override
    public void instruction() {
        System.out.println("RED Signal: Stop the Train");
    }
}

class YellowSignal extends Signal {

    @Override
    public void instruction() {
        System.out.println("YELLOW Signal: Reduce Speed");
    }
}

class GreenSignal extends Signal {

    @Override
    public void instruction() {
        System.out.println("GREEN Signal: Proceed");
    }
}

public class RailwaySignalSystem {

    public static void main(String[] args) {

        ArrayList<Signal> signals = new ArrayList<>();

        signals.add(new RedSignal());
        signals.add(new YellowSignal());
        signals.add(new GreenSignal());

        System.out.println("Railway Signal Instructions:");

        for (Signal signal : signals) {
            signal.instruction();
        }
    }
}
