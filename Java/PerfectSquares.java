public class PerfectSquares {

    //DP solution, O(n^2) time and O(n) space
    //dp[i] is the minimum number of ps that add up to i.
    //dp[i] = min{dp[i-j^2] + 1, 1 <= j^2 <= i}
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i] = i;
            for (int j = 1; j <= i/j; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    //Improvement of above
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int sqrtVal = (int)Math.sqrt(i);
            if (sqrtVal == i) {
                dp[i] = 1;
            } else {
                dp[i] = i;
                for (int j = 1; j <= sqrtVal; ++j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }

        }
        return dp[n];
    }

    //May be solved more efficiently using Lagrange's four-square theorem
}