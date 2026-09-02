import java.util.HashMap;

public class EventScheduler {

    static class Event {
        int eventId;
        String eventName;
        int startTime;

        Event(
                int eventId,
                String eventName,
                int startTime
        ) {
            this.eventId = eventId;
            this.eventName = eventName;
            this.startTime = startTime;
        }

        @Override
        public String toString() {
            return eventId + " - " +
                    eventName + " - " +
                    startTime;
        }
    }

    static class AVLNode {
        Event event;
        AVLNode left;
        AVLNode right;
        int height;

        AVLNode(Event event) {
            this.event = event;
            height = 1;
        }
    }

    private final HashMap<Integer, Event> eventMap =
            new HashMap<>();

    private AVLNode root;

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0
                : height(node.left) - height(node.right);
    }

    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    private int compare(Event first, Event second) {
        if (first.startTime != second.startTime) {
            return Integer.compare(
                    first.startTime,
                    second.startTime
            );
        }

        return Integer.compare(
                first.eventId,
                second.eventId
        );
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode newRoot = node.left;
        AVLNode temporary = newRoot.right;

        newRoot.right = node;
        node.left = temporary;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode newRoot = node.right;
        AVLNode temporary = newRoot.left;

        newRoot.left = node;
        node.right = temporary;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode insert(AVLNode node, Event event) {
        if (node == null) {
            return new AVLNode(event);
        }

        if (compare(event, node.event) < 0) {
            node.left = insert(node.left, event);
        } else {
            node.right = insert(node.right, event);
        }

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 &&
                compare(event, node.left.event) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 &&
                compare(event, node.right.event) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void addEvent(
            int eventId,
            String eventName,
            int startTime
    ) {
        if (eventMap.containsKey(eventId)) {
            System.out.println("Event ID already exists.");
            return;
        }

        Event event =
                new Event(eventId, eventName, startTime);

        eventMap.put(eventId, event);
        root = insert(root, event);
    }

    public void searchEvent(int eventId) {
        Event event = eventMap.get(eventId);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            System.out.println("Event found: " + event);
        }
    }

    private void displayInOrder(AVLNode node) {
        if (node == null) {
            return;
        }

        displayInOrder(node.left);
        System.out.println(node.event);
        displayInOrder(node.right);
    }

    public void displaySchedule() {
        System.out.println("\nEvents in chronological order:");
        displayInOrder(root);
    }

    public static void main(String[] args) {
        EventScheduler scheduler =
                new EventScheduler();

        scheduler.addEvent(1, "Java Class", 900);
        scheduler.addEvent(2, "Project Review", 1100);
        scheduler.addEvent(3, "DSA Lab", 1430);
        scheduler.addEvent(4, "Team Meeting", 1230);

        scheduler.searchEvent(2);
        scheduler.displaySchedule();
    }
}
