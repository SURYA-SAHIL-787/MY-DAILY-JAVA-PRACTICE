class LightNode {
    String color;
    LightNode next;
    LightNode(String color) { this.color = color; }
}

public class TrafficLightSystem {
    LightNode currentLight;

    public TrafficLightSystem() {
        LightNode red = new LightNode("Red");
        LightNode green = new LightNode("Green");
        LightNode yellow = new LightNode("Yellow");

        red.next = green;
        green.next = yellow;
        yellow.next = red; 

        currentLight = red;
    }

    public void changeLight() {
        if (currentLight != null) {
            currentLight = currentLight.next;
        }
    }
}
