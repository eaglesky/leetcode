class BurstBalloons {
    
    //DP, O(n^3) time and O(n^2) space
    //https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations
    //Let nums[0] = nums[n-1] = 1;
    //dp[i][j] is the max coins of bursting balloons at [i+1...j-1].
    //dp[i][j] = max{nums[t]*nums[i]*nums[j] + dp[i][t] + dp[t][j], i < t < j};
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        newNums[nums.length + 1] = 1;
        for (int i = 0; i < nums.length; ++i) {
            newNums[i + 1] = nums[i];
        }
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        for (int diff = 2; diff <= nums.length + 1; diff++) {
            for (int i = 0; i + diff < newNums.length; ++i) {
                int j = i + diff;
                dp[i][j] = 0;
                for (int t = i + 1; t < j; ++t) {
                    dp[i][j] = Math.max(dp[i][j],
                                    newNums[t]*newNums[i]*newNums[j] + dp[i][t] + dp[t][j]);
                }
            }
        }
        return dp[0][nums.length + 1];
    }
}