public class MinimumSizeSubarraySum {
    
    //Two pointers solution, O(n) time and O(1) space
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        for (int end = 0; end < nums.length; ++end) {
            sum += nums[end];
            for (; start <= end && sum >= s; ++start) {
                minLen = Math.min(minLen, end - start);
                sum -= nums[start];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen + 1;
    }

    //Binary search solution:
    //First get accumulative sum for each element. 
    //Then iterate the sum array, find the largest element
    //that is smaller than sum[i] - target + 1.
    //http://www.jyuan92.com/blog/leetcode-minimum-size-subarray-sum/
}