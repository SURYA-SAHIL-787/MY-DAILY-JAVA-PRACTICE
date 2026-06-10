import java.util.*;

public class SegmentTreeGraphQuery {
    static class SegmentTree {
        int[] tree, arr;

        SegmentTree(int[] arr) {
            this.arr = arr;
            tree = new int[arr.length * 4];
            build(1, 0, arr.length - 1);
        }

        void build(int node, int start, int end) {
            if (start == end) tree[node] = arr[start];
            else {
                int mid = (start + end) / 2;
                build(node * 2, start, mid);
                build(node * 2 + 1, mid + 1, end);
                tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
            }
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return Integer.MIN_VALUE;
            if (l <= start && end <= r) return tree[node];

            int mid = (start + end) / 2;
            return Math.max(
                query(node * 2, start, mid, l, r),
                query(node * 2 + 1, mid + 1, end, l, r)
            );
        }
    }

    public static void main(String[] args) {
        int[] nodeValues = {5, 2, 9, 1, 7, 3};
        SegmentTree st = new SegmentTree(nodeValues);

        System.out.println("Max value from index 1 to 4: " +
                st.query(1, 0, nodeValues.length - 1, 1, 4));
    }
}
