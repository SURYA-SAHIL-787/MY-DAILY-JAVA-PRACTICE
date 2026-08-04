import java.util.*;

public class AVLTaskTopologicalSort {

    // AVL Tree node
    static class AVLNode {
        String taskName;
        int height;
        AVLNode left;
        AVLNode right;

        AVLNode(String taskName) {
            this.taskName = taskName;
            this.height = 1;
        }
    }

    // Linked-list node for graph adjacency list
    static class DependencyNode {
        String taskName;
        DependencyNode next;

        DependencyNode(String taskName) {
            this.taskName = taskName;
        }
    }

    static int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    static int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    static AVLNode rotateRight(AVLNode unbalancedNode) {
        AVLNode newRoot = unbalancedNode.left;
        AVLNode transferredSubtree = newRoot.right;

        newRoot.right = unbalancedNode;
        unbalancedNode.left = transferredSubtree;

        unbalancedNode.height =
            Math.max(
                height(unbalancedNode.left),
                height(unbalancedNode.right)
            ) + 1;

        newRoot.height =
            Math.max(
                height(newRoot.left),
                height(newRoot.right)
            ) + 1;

        return newRoot;
    }

    static AVLNode rotateLeft(AVLNode unbalancedNode) {
        AVLNode newRoot = unbalancedNode.right;
        AVLNode transferredSubtree = newRoot.left;

        newRoot.left = unbalancedNode;
        unbalancedNode.right = transferredSubtree;

        unbalancedNode.height =
            Math.max(
                height(unbalancedNode.left),
                height(unbalancedNode.right)
            ) + 1;

        newRoot.height =
            Math.max(
                height(newRoot.left),
                height(newRoot.right)
            ) + 1;

        return newRoot;
    }

    static AVLNode insertTask(
            AVLNode root,
            String taskName) {

        if (root == null) {
            return new AVLNode(taskName);
        }

        int comparison =
            taskName.compareToIgnoreCase(root.taskName);

        if (comparison < 0) {
            root.left = insertTask(root.left, taskName);
        } else if (comparison > 0) {
            root.right = insertTask(root.right, taskName);
        } else {
            return root;
        }

        root.height =
            Math.max(
                height(root.left),
                height(root.right)
            ) + 1;

        int balance = getBalance(root);

        // Left-Left case
        if (balance > 1 &&
            taskName.compareToIgnoreCase(
                root.left.taskName) < 0) {

            return rotateRight(root);
        }

        // Right-Right case
        if (balance < -1 &&
            taskName.compareToIgnoreCase(
                root.right.taskName) > 0) {

            return rotateLeft(root);
        }

        // Left-Right case
        if (balance > 1 &&
            taskName.compareToIgnoreCase(
                root.left.taskName) > 0) {

            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Right-Left case
        if (balance < -1 &&
            taskName.compareToIgnoreCase(
                root.right.taskName) < 0) {

            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    static void displayTasksInOrder(AVLNode root) {
        if (root == null) {
            return;
        }

        displayTasksInOrder(root.left);
        System.out.print(root.taskName + " ");
        displayTasksInOrder(root.right);
    }

    static void addTaskToGraph(
            Map<String, DependencyNode> graph,
            Map<String, Integer> inDegree,
            String taskName) {

        graph.putIfAbsent(taskName, null);
        inDegree.putIfAbsent(taskName, 0);
    }

    static void addDependency(
            Map<String, DependencyNode> graph,
            Map<String, Integer> inDegree,
            String firstTask,
            String nextTask) {

        addTaskToGraph(graph, inDegree, firstTask);
        addTaskToGraph(graph, inDegree, nextTask);

        DependencyNode newNode =
            new DependencyNode(nextTask);

        newNode.next = graph.get(firstTask);
        graph.put(firstTask, newNode);

        inDegree.put(
            nextTask,
            inDegree.get(nextTask) + 1
        );
    }

    static void topologicalSort(
            Map<String, DependencyNode> graph,
            Map<String, Integer> inDegree) {

        Queue<String> queue = new LinkedList<>();

        for (String task : graph.keySet()) {
            if (inDegree.get(task) == 0) {
                queue.offer(task);
            }
        }

        List<String> executionOrder =
            new ArrayList<>();

        while (!queue.isEmpty()) {
            String currentTask = queue.poll();

            executionOrder.add(currentTask);

            DependencyNode dependentTask =
                graph.get(currentTask);

            while (dependentTask != null) {
                String task = dependentTask.taskName;

                inDegree.put(
                    task,
                    inDegree.get(task) - 1
                );

                if (inDegree.get(task) == 0) {
                    queue.offer(task);
                }

                dependentTask = dependentTask.next;
            }
        }

        if (executionOrder.size() != graph.size()) {
            System.out.println(
                "The dependency graph contains a cycle."
            );
            return;
        }

        System.out.println("Valid task execution order:");

        for (int i = 0; i < executionOrder.size(); i++) {
            System.out.print(executionOrder.get(i));

            if (i < executionOrder.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        String[] tasks = {
            "Design",
            "Database",
            "Coding",
            "Testing",
            "Deployment",
            "Documentation"
        };

        AVLNode root = null;

        Map<String, DependencyNode> graph =
            new HashMap<>();

        Map<String, Integer> inDegree =
            new HashMap<>();

        for (String task : tasks) {
            root = insertTask(root, task);
            addTaskToGraph(graph, inDegree, task);
        }

        addDependency(
            graph,
            inDegree,
            "Design",
            "Coding"
        );

        addDependency(
            graph,
            inDegree,
            "Database",
            "Coding"
        );

        addDependency(
            graph,
            inDegree,
            "Coding",
            "Testing"
        );

        addDependency(
            graph,
            inDegree,
            "Documentation",
            "Deployment"
        );

        addDependency(
            graph,
            inDegree,
            "Testing",
            "Deployment"
        );

        System.out.println(
            "Tasks stored alphabetically in AVL Tree:"
        );

        displayTasksInOrder(root);
        System.out.println("\n");

        topologicalSort(graph, inDegree);
    }
}
