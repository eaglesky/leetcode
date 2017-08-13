public class MinimumPathSum {
    
    //O(mn) time and O(n) space
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] dp = new int[grid[0].length];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                int preSum = 0;
                //This logic could be simpler if using the assumption that the numbers
                //are non-negative
                if (i > 0 && j > 0) {
                    preSum = Math.min(dp[j-1], dp[j]);
                } else if (i > 0) {
                    preSum = dp[j];
                } else if (j > 0) {
                    preSum = dp[j - 1];
                }
                dp[j] = grid[i][j] + preSum;
            }
        }
        return dp[grid[0].length - 1];
    }
}