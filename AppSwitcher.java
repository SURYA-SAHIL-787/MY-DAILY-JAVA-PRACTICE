class AppNode {
    String appName;
    AppNode next;
    AppNode(String appName) { this.appName = appName; }
}

public class AppSwitcher {
    AppNode head = null;
    AppNode tail = null;
    AppNode currentFocus = null;

    public void openApp(String appName) {
        AppNode newApp = new AppNode(appName);
        if (head == null) {
            head = newApp;
            tail = newApp;
            newApp.next = head;
        } else {
            tail.next = newApp;
            tail = newApp;
            tail.next = head;
        }
        currentFocus = newApp; 
    }

    public void altTab() {
        if (currentFocus != null) {
            currentFocus = currentFocus.next;
        }
    }
}
