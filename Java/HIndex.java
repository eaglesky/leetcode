public class HIndex {
    
    //Naive solution. O(nlogn) time
    public int hIndex0(int[] citations) {
        Arrays.sort(citations);
        int i = 1;
        for(; i <= citations.length && i <= citations[citations.length - i]; ++i);
        return i - 1;
    }
    
    //Linear solution.
    public int hIndex1(int[] citations) {
        int maxNum = 0;
        for (int citation : citations) {
            maxNum = Math.max(maxNum, citation);
        }
        int[] counts = new int[maxNum + 1];
        for (int citation : citations) {
            counts[citation]++;
        }
        int i = maxNum;
        for (; i >= 0; --i) {
            if (i < maxNum) {
                counts[i] += counts[i + 1];
            }
            if (counts[i] >= i) {
                break;
            }
        }
        return i;
    }
    
    //Improve the above solution to get the best O(n) solution.
    public int hIndex(int[] citations) {
        int[] counts = new int[citations.length + 1];
        for (int citation : citations) {
            if (citation >= citations.length) {
                counts[citations.length]++;
            } else {
                counts[citation]++;
            }
        }
        int countAbove = 0;
        for (int i = counts.length - 1; i >= 0; --i) {
            countAbove += counts[i];
            if (countAbove >= i) {
                return i;
            }
        }
        return 0;
    }
}