public class PalindromeLinkedListString {
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
        }
    }

    static boolean isPalindrome(Node head) {
        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append(head.data);
            head = head.next;
        }

        String s = sb.toString();
        return s.equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        Node head = new Node('m');
        head.next = new Node('a');
        head.next.next = new Node('d');
        head.next.next.next = new Node('a');
        head.next.next.next.next = new Node('m');

        System.out.println(isPalindrome(head));
    }
}
