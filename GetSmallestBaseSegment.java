public class GetSmallestBaseSegment {

    public static String getSmallestBaseSegment(
            String missingData, int segmentSize) {

        int n = missingData.length();

        if (segmentSize <= 0 || segmentSize > n) {
            return "-1";
        }

        int[] required = new int[26];
        int[] window = new int[26];

        for (char c : missingData.toCharArray()) {
            required[c - 'a']++;
        }

        for (int i = 0; i < segmentSize; i++) {
            window[missingData.charAt(i) - 'a']++;
        }

        String answer = null;
        int minRepetitions = Integer.MAX_VALUE;

        for (int start = 0; start <= n - segmentSize; start++) {

            int repetitions = 0;
            boolean valid = true;

            for (int c = 0; c < 26; c++) {

                if (required[c] == 0) {
                    continue;
                }

                if (window[c] == 0) {
                    valid = false;
                    break;
                }

                int copies = (required[c] - 1) / window[c] + 1;

                repetitions = Math.max(repetitions, copies);
            }

            if (valid && repetitions <= minRepetitions) {

                String candidate = missingData.substring(
                        start, start + segmentSize);

                if (answer == null
                        || repetitions < minRepetitions
                        || candidate.compareTo(answer) < 0) {

                    minRepetitions = repetitions;
                    answer = candidate;
                }
            }

            if (start + segmentSize < n) {
                window[missingData.charAt(start) - 'a']--;

                window[missingData.charAt(
                        start + segmentSize) - 'a']++;
            }
        }

        return answer == null ? "-1" : answer;
    }

    public static void main(String[] args) {
        String missingData = "aavvavv";
        int segmentSize = 2;

        System.out.println(
                getSmallestBaseSegment(missingData, segmentSize));
    }
}
