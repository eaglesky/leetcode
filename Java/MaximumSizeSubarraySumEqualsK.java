/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
*/
import java.util.*;

public class MaximumSizeSubarraySumEqualsK {

	//O(n) time and O(n) space
    public int maxSubArrayLen(int[] nums, int k) {
    	Map<Integer, Integer> numToId = new HashMap<>();
    	numToId.put(0, -1);
    	int sum = 0;
    	int maxLen = 0;
    	for (int i = 0; i < nums.length; ++i) {
    		sum += nums[i];
    		Integer prevId = numToId.get(sum - k);
    		if (prevId != null) {
    			maxLen = Math.max(maxLen, i - prevId);
    		}
    		numToId.putIfAbsent(sum, i);
    	}
    	return maxLen;
    }

    public static void main(String[] args) {
    	MaximumSizeSubarraySumEqualsK solution = new MaximumSizeSubarraySumEqualsK();
    	int[][] tests = new int[][] {
    		{1, -1, 5, -2, 3},
    		{-2, -1, 2, 1},
    		{2, -1, 3, -6, 15, -30},
    		{4},
    		{-2}
    	};
    	int[] ks = new int[]{3, 1, -17, 0, -2};
    	for (int i = 0; i < tests.length; ++i) {
    		int[] test = tests[i];
    		int k = ks[i];
    		System.out.println(Arrays.toString(test) + ", k = " + k);
    		int len = solution.maxSubArrayLen(test, k);
    		System.out.println("len = " + len);
    		System.out.println("");
    	}
    }
}