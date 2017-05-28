public class Solution {
    
    //Naive O(n) solution
    public int hIndex0(int[] citations) {
        int i = 1;
        for(; i <= citations.length && i <= citations[citations.length - i]; ++i);
        return i - 1;
    }
    
    //Best O(logn) binary search solution
    public int hIndex(int[] citations) {
        int low = 0;
        int high = citations.length;
        while (low < high) {
            int mid = high - ((high - low) >> 1);
            if (citations[citations.length - mid] >= mid) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}