import java.util.*;

class Component {

    private int componentId;
    private String componentName;

    public Component(int componentId, String componentName) {
        this.componentId = componentId;
        this.componentName = componentName;
    }

    public void displayComponent() {
        System.out.println(componentId + " - " + componentName);
    }
}

public class ComponentInspectionStack {

    public static void main(String[] args) {

        Stack<Component> components = new Stack<>();

        components.push(new Component(101, "Engine"));
        components.push(new Component(102, "Transmission"));
        components.push(new Component(103, "Battery"));
        components.push(new Component(104, "Brake Assembly"));

        System.out.println("Components waiting for inspection:");

        for (Component component : components) {
            component.displayComponent();
        }

        System.out.println("\nInspection Order:");

        while (!components.isEmpty()) {

            Component component = components.pop();

            component.displayComponent();
        }
    }
}
