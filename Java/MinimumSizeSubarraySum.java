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

    //Better version of above. minLen should be 0 initially,
    //since the size of nums could be Integer.MAX_VALUE
    //and s is equal to the sum of all the elements.
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = 0;
        int curSum = 0;
        int start = 0;
        for (int i = 0; i < nums.length; ++i) {
            curSum += nums[i];
            for(; curSum >= s; start++) {
                int curLen = i - start + 1;
                minLen = (minLen == 0 || minLen > curLen) ? curLen : minLen;
                curSum -= nums[start];
            }
        }
        return minLen;
    }

    //Binary search solution:
    //First get accumulative sum for each element. 
    //Then iterate the sum array, find the largest element
    //that is smaller than sum[i] - target + 1.
    //http://www.jyuan92.com/blog/leetcode-minimum-size-subarray-sum/
}