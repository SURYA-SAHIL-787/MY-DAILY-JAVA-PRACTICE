import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumStringCostTree {

    static List<Integer>[] tree;
    static String[] words;
    static int[] minimumCost;

    /*
     * Calculates the edit distance between two strings.
     *
     * Allowed operations:
     * 1. Insert a character
     * 2. Delete a character
     * 3. Replace a character
     */
    static int editDistance(String first, String second) {
        int rows = first.length();
        int columns = second.length();

        int[][] dp = new int[rows + 1][columns + 1];

        for (int i = 0; i <= rows; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= columns; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {

                if (first.charAt(i - 1)
                        == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(
                            replace,
                            Math.min(insert, delete)
                    );
                }
            }
        }

        return dp[rows][columns];
    }

    /*
     * Returns the minimum cost from the current node
     * to any leaf in its subtree.
     */
    static int dfs(int node, int parent) {
        boolean isLeaf = true;
        int bestChildCost = Integer.MAX_VALUE;

        for (int neighbour : tree[node]) {
            if (neighbour == parent) {
                continue;
            }

            isLeaf = false;

            int edgeCost = editDistance(
                    words[node],
                    words[neighbour]
            );

            int totalCost =
                    edgeCost + dfs(neighbour, node);

            bestChildCost = Math.min(
                    bestChildCost,
                    totalCost
            );
        }

        if (isLeaf) {
            minimumCost[node] = 0;
        } else {
            minimumCost[node] = bestChildCost;
        }

        return minimumCost[node];
    }

    static void addEdge(int first, int second) {
        tree[first].add(second);
        tree[second].add(first);
    }

    public static void main(String[] args) {
        int numberOfNodes = 7;

        words = new String[]{
                "cat",
                "bat",
                "coat",
                "ball",
                "bad",
                "boat",
                "goat"
        };

        tree = new ArrayList[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            tree[i] = new ArrayList<>();
        }

        /*
                   cat
                 /     \
               bat     coat
              /   \    /  \
           ball   bad boat goat
        */

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 5);
        addEdge(2, 6);

        minimumCost = new int[numberOfNodes];
        Arrays.fill(minimumCost, -1);

        int answer = dfs(0, -1);

        System.out.println(
                "Minimum root-to-leaf string cost: "
                        + answer
        );

        System.out.println("\nMinimum cost from every node:");

        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println(
                    words[i] + " -> " + minimumCost[i]
            );
        }
    }
}
