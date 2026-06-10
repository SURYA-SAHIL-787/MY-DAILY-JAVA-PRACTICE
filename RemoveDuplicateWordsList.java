import java.util.*;

public class RemoveDuplicateWordsList {
    static class Node {
        String word;
        Node next;

        Node(String word) {
            this.word = word;
        }
    }

    static void removeDuplicates(Node head) {
        HashSet<String> set = new HashSet<>();
        Node curr = head, prev = null;

        while (curr != null) {
            if (set.contains(curr.word)) {
                prev.next = curr.next;
            } else {
                set.add(curr.word);
                prev = curr;
            }
            curr = curr.next;
        }
    }

    static void print(Node head) {
        while (head != null) {
            System.out.print(head.word + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node("java");
        head.next = new Node("code");
        head.next.next = new Node("java");
        head.next.next.next = new Node("list");

        removeDuplicates(head);
        print(head);
    }
}
