public class Solution {
    
    //d[i] = max{d[j], 0 <= j < i - 1} + nums[i]
    //Result = max{d[i], 0 <= i < nums.length}
    //For each i, prev2 = nums[i-2], prev1 = nums[i-1]
    //prevMax = max{d[j], 0 <= j < i - 1}
    //O(n) time and O(1) space
    public int rob0(int[] nums) {
        int prevMax = 0;
        int maxMoney = 0;
        int prev1 = 0, prev2 = 0;
        for (int i = 0; i < nums.length; ++i) {
            int cur = prevMax + nums[i];
            maxMoney = Math.max(maxMoney, cur);
            prev2 = prev1;
            prev1 = cur;
            prevMax = Math.max(prevMax, prev2);
        }
        return maxMoney;
    }
    
    //A simpler solution
    //  dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
    //  dp[i][1] = num[i - 1] + dp[i - 1][0];
    //  dp[i][1] means we rob the current house and dp[i][0] means we don't
    // O(n) time and O(1) space
    public int rob(int[] nums) {
        int preNo = 0;
        int preYes = 0;
        for (int i = 0; i < nums.length; ++i) {
            int curNo = Math.max(preNo, preYes);
            int curYes = preNo + nums[i];
            preNo = curNo;
            preYes = curYes;
        }
        return Math.max(preNo, preYes);
    }
}