import java.util.*;

class Fan {

    private int fanId;
    private String fanName;

    public Fan(int fanId, String fanName) {
        this.fanId = fanId;
        this.fanName = fanName;
    }

    public void displayFan() {
        System.out.println(
            "Fan ID: " + fanId +
            " | Name: " + fanName
        );
    }
}

public class FootballTicketQueue {

    public static void main(String[] args) {

        Queue<Fan> ticketQueue = new LinkedList<>();

        ticketQueue.add(new Fan(101, "Sahil"));
        ticketQueue.add(new Fan(102, "Vijay"));
        ticketQueue.add(new Fan(103, "Akash"));
        ticketQueue.add(new Fan(104, "Rohan"));

        System.out.println("Fans Waiting for Tickets:");

        for (Fan fan : ticketQueue) {
            fan.displayFan();
        }

        System.out.println("\nTicket Issue Order:");

        while (!ticketQueue.isEmpty()) {
            ticketQueue.poll().displayFan();
        }
    }
}
