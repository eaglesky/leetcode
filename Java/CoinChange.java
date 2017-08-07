public class CoinChange {
    
    //DP solution
    //Let dp[a] be the number of coins that have sum of a.
    //dp[a] = min{dp[a - ak], ak exists in coins and ak <= a, and dp[a - ak] >= 0} + 1
    //or -1 if no combination exists that have sum equals a.
    //O(amount * coins.length) time and O(amount) space.
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return amount == 0 ? 0 : -1;
        }
        //Assuming amount is positive and each coin value is positive too
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        for (int sum = 1; sum <= amount; ++sum) {
            dp[sum] = -1;
            for (int i = 0; i < coins.length && coins[i] <= sum; ++i) {
                int prev = dp[sum - coins[i]];
                if (prev >= 0 && (dp[sum] < 0 || (prev + 1 < dp[sum]))) {
                    dp[sum] = prev + 1;
                }
            }
        }
        return dp[amount];
    }
}