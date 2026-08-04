public class FolderTreeLinkedPath {

    // Folder tree node
    static class Folder {
        String name;
        ChildNode firstChild;

        Folder(String name) {
            this.name = name;
        }

        void addChild(Folder childFolder) {
            ChildNode newNode = new ChildNode(childFolder);

            if (firstChild == null) {
                firstChild = newNode;
                return;
            }

            ChildNode current = firstChild;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }
    }

    // Linked-list node for folder children
    static class ChildNode {
        Folder folder;
        ChildNode next;

        ChildNode(Folder folder) {
            this.folder = folder;
        }
    }

    // Linked list used to store the folder path
    static class PathNode {
        String folderName;
        PathNode next;

        PathNode(String folderName) {
            this.folderName = folderName;
        }
    }

    static class PathList {
        PathNode head;
        PathNode tail;

        void addLast(String folderName) {
            PathNode newNode = new PathNode(folderName);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        void removeLast() {
            if (head == null) {
                return;
            }

            if (head == tail) {
                head = null;
                tail = null;
                return;
            }

            PathNode current = head;

            while (current.next != tail) {
                current = current.next;
            }

            current.next = null;
            tail = current;
        }

        void displayPath() {
            PathNode current = head;

            while (current != null) {
                System.out.print(current.folderName);

                if (current.next != null) {
                    System.out.print(" -> ");
                }

                current = current.next;
            }

            System.out.println();
        }
    }

    static boolean searchFolder(
            Folder currentFolder,
            String targetName,
            PathList path) {

        if (currentFolder == null) {
            return false;
        }

        path.addLast(currentFolder.name);

        if (currentFolder.name.equalsIgnoreCase(targetName)) {
            return true;
        }

        ChildNode child = currentFolder.firstChild;

        while (child != null) {
            if (searchFolder(child.folder, targetName, path)) {
                return true;
            }

            child = child.next;
        }

        path.removeLast();
        return false;
    }

    static void displayTree(Folder folder, int level) {
        if (folder == null) {
            return;
        }

        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }

        System.out.println("|-- " + folder.name);

        ChildNode child = folder.firstChild;

        while (child != null) {
            displayTree(child.folder, level + 1);
            child = child.next;
        }
    }

    public static void main(String[] args) {

        Folder root = new Folder("Computer");

        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder projects = new Folder("Projects");

        Folder java = new Folder("Java");
        Folder python = new Folder("Python");
        Folder college = new Folder("College");

        Folder trees = new Folder("Trees");
        Folder graphs = new Folder("Graphs");

        root.addChild(documents);
        root.addChild(pictures);
        root.addChild(projects);

        documents.addChild(college);

        projects.addChild(java);
        projects.addChild(python);

        java.addChild(trees);
        java.addChild(graphs);

        System.out.println("Folder structure:");

        displayTree(root, 0);

        String targetFolder = "Graphs";
        PathList path = new PathList();

        System.out.println("\nSearching for folder: " + targetFolder);

        if (searchFolder(root, targetFolder, path)) {
            System.out.println("Folder path:");
            path.displayPath();
        } else {
            System.out.println("Folder not found.");
        }
    }
}
