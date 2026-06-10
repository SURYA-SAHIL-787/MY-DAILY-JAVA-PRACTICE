public class StringLinkedListSearch {
    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }

    static boolean search(Node head, String key) {
        while (head != null) {
            if (head.data.equals(key)) return true;
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Node head = new Node("apple");
        head.next = new Node("banana");
        head.next.next = new Node("mango");

        System.out.println(search(head, "banana"));
    }
}
