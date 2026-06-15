import java.util.*;

public class NonOverlappingIntervals {

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int countKept = 1;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= lastEnd) {
                countKept++;
                lastEnd = intervals[i][1];
            }
        }

        return intervals.length - countKept;
    }

    public static void main(String[] args) {
        int[][] intervals = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 3}
        };

        System.out.println(eraseOverlapIntervals(intervals));
    }
}
