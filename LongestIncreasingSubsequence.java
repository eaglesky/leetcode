public class LongestIncreasingSubsequence {
    
    //DP solution, O(n^2) time and O(n) space
    public int lengthOfLIS0(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        int[] lens = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; --i) {
            lens[i] = 1;
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[j] > nums[i]) {
                    lens[i] = Math.max(lens[i], lens[j] + 1);
                }
            }
            result = Math.max(result, lens[i]);
        }
        return result;
    }
    
    //Binary search solution(greedy), O(nlogn) time and O(n) space
    //increasingNums stores the increasing elements found so far, which are strictly increasing
    //(no duplicate). After the last element of the longest increasing subsequence 
    //is scanned, it must be located at the last element in increasingNums.
    //Thus after the loop finishes, the size of increasingNums is the length of
    //longest increasing subsequence.
    //This algorithm is easy to prove but very hard to come up with.
    //It can not give what the LIS is, only the length.
    private int binarySearch(int[] nums, int len, int target) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    public int lengthOfLIS(int[] nums) {
        int[] increasingNums = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            if (len == 0 || num > increasingNums[len-1]) {
                increasingNums[len++] = num;
            } else {
                int pos = binarySearch(increasingNums, len, num);
                increasingNums[pos] = num;                
            }
        }
        return len;
    }
}