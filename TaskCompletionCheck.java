import java.util.*;

public class TaskCompletionCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph.get(a).add(b);
            indegree[b]++;
        }

        boolean possible = canFinishTasks(graph, indegree, n);

        if (possible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        sc.close();
    }

    static boolean canFinishTasks(ArrayList<ArrayList<Integer>> graph, int[] indegree, int n) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int completedTasks = 0;

        while (!queue.isEmpty()) {
            int task = queue.poll();
            completedTasks++;

            for (int nextTask : graph.get(task)) {
                indegree[nextTask]--;

                if (indegree[nextTask] == 0) {
                    queue.add(nextTask);
                }
            }
        }

        return completedTasks == n;
    }
}
