import java.util.*;

class Book {
    int id;
    String title;
    boolean available;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
    }
}

class LibraryRepository {
    HashMap<Integer, Book> books = new HashMap<>();

    void addBook(Book book) {
        books.put(book.id, book);
    }

    Book findBook(int id) {
        return books.get(id);
    }

    Collection<Book> getBooks() {
        return books.values();
    }
}

class LibraryService {

    LibraryRepository repo = new LibraryRepository();
    Queue<Integer> requests = new LinkedList<>();

    void addBook(Book book) {
        repo.addBook(book);
        System.out.println("Book Added.");
    }

    void requestBook(int id) {
        Book book = repo.findBook(id);

        if (book != null && book.available) {
            requests.offer(id);
            System.out.println("Book Request Added.");
        } else {
            System.out.println("Book Not Available.");
        }
    }

    void issueBook() {
        if (requests.isEmpty()) {
            System.out.println("No Pending Requests.");
            return;
        }

        int id = requests.poll();
        Book book = repo.findBook(id);
        book.available = false;

        System.out.println("Issued : " + book.title);
    }

    void displayBooks() {
        for (Book book : repo.getBooks()) {
            System.out.println(book.id + " " + book.title +
                    " Available: " + book.available);
        }
    }
}

public class NewLibraryManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        while (true) {

            System.out.println("\n1.Add Book");
            System.out.println("2.Request Book");
            System.out.println("3.Issue Book");
            System.out.println("4.Display Books");
            System.out.println("5.Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Book Title: ");
                    String title = sc.nextLine();

                    service.addBook(new Book(id, title));
                    break;

                case 2:
                    System.out.print("Book ID: ");
                    service.requestBook(sc.nextInt());
                    break;

                case 3:
                    service.issueBook();
                    break;

                case 4:
                    service.displayBooks();
                    break;

                case 5:
                    return;
            }
        }
    }
}
