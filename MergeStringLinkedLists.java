public class MergeStringLinkedLists {
    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }

    static Node merge(Node a, Node b) {
        Node dummy = new Node("");
        Node temp = dummy;

        while (a != null && b != null) {
            if (a.data.compareTo(b.data) <= 0) {
                temp.next = a;
                a = a.next;
            } else {
                temp.next = b;
                b = b.next;
            }
            temp = temp.next;
        }

        temp.next = (a != null) ? a : b;
        return dummy.next;
    }

    static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node a = new Node("apple");
        a.next = new Node("mango");

        Node b = new Node("banana");
        b.next = new Node("orange");

        print(merge(a, b));
    }
}
