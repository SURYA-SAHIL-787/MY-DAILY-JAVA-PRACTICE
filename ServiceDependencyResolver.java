import java.util.*;

public class ServiceDependencyResolver {
    public static int[] findStartupOrder(int numServices, int[][] dependencies) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numServices];

        for (int i = 0; i < numServices; i++) adj.add(new ArrayList<>());

        for (int[] dep : dependencies) {
            adj.get(dep[1]).add(dep[0]); // dep[1] must run before dep[0]
            inDegree[dep[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numServices; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[numServices];
        int index = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[index++] = current;

            for (int next : adj.get(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(next);
            }
        }

        return (index == numServices) ? order : new int[0];
    }

    public static void main(String[] args) {
        int[][] deps = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(findStartupOrder(4, deps)));
    }
}
