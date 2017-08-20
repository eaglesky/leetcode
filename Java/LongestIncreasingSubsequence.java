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
    
    //More natural DP solution:
    //O(n^2) time and O(n) space
    //The longest length ending with nums[i]:
    //dp[i] = max{0, {dp[t], 0 <= t < i && nums[t] < nums[i]}} + 1
    public int lengthOfLIS(int[] nums) {
        int maxLen = 0;
        if (nums == null || nums.length == 0) {
            return maxLen;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            int maxPrevLen = 0;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    maxPrevLen = Math.max(maxPrevLen, dp[j]);
                }
            }
            dp[i] = maxPrevLen + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    //Binary search solution(greedy), O(nlogn) time and O(n) space
    //increasingNums stores the increasing elements found so far, which are strictly increasing
    //(no duplicate). d[i] is the current min value at the end of a increasing subsequence
    //with length i + 1. So when nums[i] comes in, find the ceiling element of it at j,
    //and update d[j] with nums[i]. That's the only element that can be updated. Also j + 1
    //is the max length of the increasing subsequence ending with d[j].
    //This solution can not give what the LIS is, only the length. To reconstruct the LIS,
    //we need to maintain a map of current num to previous num in LIS and insert into it
    //each time we iterate a num.
    //https://discuss.leetcode.com/topic/28719/short-java-solution-using-dp-o-n-log-n/3?page=1
    //Still don't know how to come up with this solution!
    
    //Find the smallest id that has num no less than target.
    private int binarySearch(int[] nums, int len, int target) {
        int low = 0;
        int high = len;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target <= nums[mid]) {
                high = mid;
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