import java.util.*;

class Book {
    private String title;
    private int quantity;
    private Queue<String> waitingList;

    public Book(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
        this.waitingList = new LinkedList<>();
    }

    public String getTitle() {
        return title;
    }

    public void requestBook(String studentName) {
        if (quantity > 0) {
            quantity--;
            System.out.println(studentName + " borrowed " + title);
        } else {
            waitingList.offer(studentName);
            System.out.println(studentName + " added to waiting list for " + title);
        }
    }

    public void returnBook() {
        if (!waitingList.isEmpty()) {
            String student = waitingList.poll();
            System.out.println(title + " assigned to waiting student: " + student);
        } else {
            quantity++;
            System.out.println(title + " returned successfully.");
        }
    }

    public void displayStatus() {
        System.out.println("\nBook: " + title);
        System.out.println("Available Copies: " + quantity);
        System.out.println("Waiting List: " + waitingList);
    }
}

public class LibraryRequestSystem {

    public static void main(String[] args) {

        Book book = new Book("Data Structures", 2);

        book.requestBook("Aman");
        book.requestBook("Rahul");
        book.requestBook("Surya");
        book.requestBook("Kiran");

        book.displayStatus();

        System.out.println("\nReturning a book...");
        book.returnBook();

        book.displayStatus();
    }
}
