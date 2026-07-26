import java.util.*;

class BrowserHistory {
    private String currentPage;

    private final Deque<String> backStack =
            new ArrayDeque<>();

    private final Deque<String> forwardStack =
            new ArrayDeque<>();

    BrowserHistory(String homePage) {
        currentPage = homePage;
    }

    void visit(String url) {
        backStack.push(currentPage);
        currentPage = url;
        forwardStack.clear();

        showCurrentPage();
    }

    void goBack() {
        if (backStack.isEmpty()) {
            System.out.println("No previous page");
            return;
        }

        forwardStack.push(currentPage);
        currentPage = backStack.pop();

        showCurrentPage();
    }

    void goForward() {
        if (forwardStack.isEmpty()) {
            System.out.println("No forward page");
            return;
        }

        backStack.push(currentPage);
        currentPage = forwardStack.pop();

        showCurrentPage();
    }

    void showCurrentPage() {
        System.out.println("Current page: " + currentPage);
    }
}

public class BrowserHistoryManager {
    public static void main(String[] args) {
        BrowserHistory history =
                new BrowserHistory("google.com");

        history.showCurrentPage();
        history.visit("openai.com");
        history.visit("github.com");

        history.goBack();
        history.goBack();
        history.goForward();

        history.visit("stackoverflow.com");
        history.goForward();
    }
}
