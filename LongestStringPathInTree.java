import java.util.ArrayList;
import java.util.List;

public class LongestStringPathInTree {

    static List<Integer>[] tree;
    static String[] words;
    static int[] dp;
    static int longestPath = 1;

    /*
     * dp[node] stores the longest valid downward path
     * starting from the current node.
     */
    static void dfs(int node, int parent) {
        dp[node] = 1;

        for (int neighbour : tree[node]) {
            if (neighbour == parent) {
                continue;
            }

            dfs(neighbour, node);

            String parentWord = words[node];
            String childWord = words[neighbour];

            char lastCharacter =
                    parentWord.charAt(parentWord.length() - 1);

            char firstCharacter = childWord.charAt(0);

            if (lastCharacter == firstCharacter) {
                dp[node] = Math.max(
                        dp[node],
                        1 + dp[neighbour]
                );
            }
        }

        longestPath = Math.max(longestPath, dp[node]);
    }

    static void addEdge(int first, int second) {
        tree[first].add(second);
        tree[second].add(first);
    }

    public static void main(String[] args) {
        int numberOfNodes = 7;

        words = new String[]{
                "java",
                "apple",
                "ant",
                "egg",
                "tree",
                "go",
                "orange"
        };

        tree = new ArrayList[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            tree[i] = new ArrayList<>();
        }

        /*
                 java
                /    \
             apple   ant
             /   \     \
           egg   tree   go
                         \
                        orange
        */

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 5);
        addEdge(5, 6);

        dp = new int[numberOfNodes];

        dfs(0, -1);

        System.out.println(
                "Longest character-matching path length: "
                        + longestPath
        );

        System.out.println("\nDP value of every node:");

        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println(
                    words[i] + " -> " + dp[i]
            );
        }
    }
}
