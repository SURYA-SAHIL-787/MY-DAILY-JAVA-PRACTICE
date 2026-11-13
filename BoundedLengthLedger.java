import java.util.Arrays;

public class BoundedLengthLedger {
    public static long count(int[] amounts, int minLength, int maxLength,
                             long low, long high) {
        int n = amounts.length;
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + amounts[i];
        }

        long[] sorted = prefix.clone();
        Arrays.sort(sorted);
        int[] tree = new int[n + 2];
        long answer = 0;

        for (int end = minLength; end <= n; end++) {
            int entering = lowerBound(sorted, prefix[end - minLength]) + 1;
            add(tree, entering, 1);

            int expired = end - maxLength - 1;
            if (expired >= 0) {
                add(tree, lowerBound(sorted, prefix[expired]) + 1, -1);
            }

            int from = lowerBound(sorted, prefix[end] - high);
            int to = lowerBound(sorted, prefix[end] - low + 1);
            answer += sum(tree, to) - sum(tree, from);
        }

        return answer;
    }

    private static int lowerBound(long[] values, long target) {
        int left = 0;
        int right = values.length;

        while (left < right) {
            int middle = (left + right) >>> 1;
            if (values[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

    private static void add(int[] tree, int index, int delta) {
        for (; index < tree.length; index += index & -index) {
            tree[index] += delta;
        }
