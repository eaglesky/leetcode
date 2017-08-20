class RussianDollEnvelopes {
    
    //O(nlogn) time and O(n) space
    //https://discuss.leetcode.com/topic/47469/java-nlogn-solution-with-explanation
    //It's easy to prove that the result is length of valid LIS.
    //If there is a longer length, that could happen only when there are duplicate
    //widths, and when the sorted array has a different order -- the relative order
    //of envelopes with same width changes. If that could increase the length of LIS
    //in heights, that would mean the new LIS includes duplicate widths, which makes
    //this new LIS invalid. So there must be no longer length of valid LIS.
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o2[1], o1[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int n = envelopes.length;
        int[] increasingHeights = new int[n];
        int len = 0;
        for (int[] envelope : envelopes) {
            if (len == 0 || envelope[1] > increasingHeights[len - 1]) {
                increasingHeights[len++] = envelope[1];
            } else {
                int id = Arrays.binarySearch(increasingHeights, 0, len, envelope[1]);
                if (id < 0) {
                    id = -id - 1;
                    increasingHeights[id] = envelope[1];
                }
            }
        }
        return len;
    }
}