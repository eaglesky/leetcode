class BestTimeToBuyAndSellStock4 {
    
    private int getMaxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i-1]) {
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }
    
    //DP solution, O(nk) time and O(k) space, n is the number of prices
    //Do nothing: dp[l][0] = 0;
    //buy:          dp[l][1] = max(dp[l-1][0] - a[l-1], dp[l-1][1])
    //buy, sell:    dp[l][2] = max(dp[l-1][1] + a[l-1], dp[l-1][2])
    //b, s, b:      dp[l][3] = max(dp[l-1][2] - a[l-1], dp[l-1][3])
    //b, s, b, s:   dp[l][4] = max(dp[l-1][3] + a[l-1], dp[l-1][4])
    // ...
    //b, s, b, s.. b, s: dp[l][2k] = max(dp[l-1][2k-1] + a[l-1], dp[l-1][2k])
    //Note that the number of arising segements is at most upper(n / 2).
    //So if k > n/2, the maxProfit is equal to the one without limiting the number
    //of transactions.
    public int maxProfit(int k, int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length == 0 || k <= 0) {
            return maxProfit;
        }
        if (k > prices.length / 2) {
            return getMaxProfit(prices);
        }
        int[] dp = new int[2*k + 1];
        for (int i = 1; i < dp.length; i += 2) {
            dp[i] = -prices[0];
        }
        for (int l = 1; l <= prices.length; ++l) {
            for (int i = 2*k; i >= 1; --i) {
                if ((i & 1) == 1) {
                    dp[i] = Math.max(dp[i-1] - prices[l-1], dp[i]);
                } else {
                    dp[i] = Math.max(dp[i-1] + prices[l-1], dp[i]);
                }
            }
        }
        for (int i = 2; i < dp.length; i += 2) {
            maxProfit = Math.max(maxProfit, dp[i]);
        }
        return maxProfit;
    }

    //DP solution that generalizes to k transactions, O(kn) time and O(n) space
    //d[k][j] is the max profit of exact k transactions that sells the last transaction at day j.
    //d[k][j] = max(d[k][j-1] + p[j] - p[j-1], max{d[k-1][t], 0 <= t < j})
    //Another similar solution is to find all the diffs between adjacent prices
    //and convert this problem to max m subarrays problem
    private static int fastResolve(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i-1]) {
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        int k = 2;
        int maxProfit = 0;
        if (prices.length == 0) {
            return maxProfit;
        } else if (k >= prices.length / 2) {
            return fastResolve(prices);
        }
        int[] profits = new int[prices.length];
        int[] prevProfits = new int[prices.length];
        for (int i = 1; i <= k; ++i) {
            int prevMax = prevProfits[0];
            for (int j = 1; j < prices.length; ++j) {
                profits[j] = Math.max(profits[j-1] + prices[j] - prices[j-1], prevMax);
                maxProfit = Math.max(maxProfit, profits[j]);
                prevMax = Math.max(prevMax, prevProfits[j]);
            }
            int[] temp = prevProfits;
            prevProfits = profits;
            profits = temp;
        }
        return maxProfit;
    }

}