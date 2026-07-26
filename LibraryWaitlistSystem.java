import java.util.*;

class Book {
    private final String title;
    private boolean available = true;

    Book(String title) {
        this.title = title;
    }

    String getTitle() { return title; }
    boolean isAvailable() { return available; }
    void issue() { available = false; }
    void returnBook() { available = true; }
}

public class LibraryWaitlistSystem {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Queue<String>> waitlists = new HashMap<>();

    void addBook(String title) {
        books.put(title, new Book(title));
        waitlists.put(title, new LinkedList<>());
    }

    void requestBook(String title, String student) {
        Book book = books.get(title);

        if (book == null) {
            System.out.println("Book not found");
        } else if (book.isAvailable()) {
            book.issue();
            System.out.println(student + " received " + title);
        } else {
            waitlists.get(title).offer(student);
            System.out.println(student + " added to waitlist");
        }
    }

    void returnBook(String title) {
        Book book = books.get(title);
        Queue<String> queue = waitlists.get(title);

        if (book == null) return;

        if (queue.isEmpty()) {
            book.returnBook();
            System.out.println(title + " is now available");
        } else {
            String nextStudent = queue.poll();
            System.out.println(title + " assigned to " + nextStudent);
        }
    }

    public static void main(String[] args) {
        LibraryWaitlistSystem library = new LibraryWaitlistSystem();

        library.addBook("Java Basics");
        library.requestBook("Java Basics", "Asha");
        library.requestBook("Java Basics", "Ravi");
        library.returnBook("Java Basics");
    }
}
