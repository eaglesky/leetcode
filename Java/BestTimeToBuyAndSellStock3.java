import java.util.*;

public class BestTimeToBuyAndSellStock3 {
    
    //Two passes solution, O(n) time and O(n) space.
    public int maxProfit0(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] maxProfits = new int[prices.length];
        int maxProfit = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; ++i) {
            min = Math.min(min, prices[i]);
            maxProfits[i] = prices[i] - min;
        }
        int max = Integer.MIN_VALUE;
        int totalMaxProfit = 0;
        maxProfit = 0;
        for (int i = prices.length - 1; i >= 0; --i) {
            max = Math.max(max, prices[i]);
            maxProfit = Math.max(maxProfit, max - prices[i]);
            totalMaxProfit = Math.max(totalMaxProfit, maxProfits[i] + maxProfit);
        }
        return totalMaxProfit;
    }

    //Four passes solution, O(n) time and O(1) space. Not easy to use in interview
    //See BestTimeToBuy3.cc.
    //https://discuss.leetcode.com/topic/902/don-t-need-dp-to-solve-it-within-o-n
    //The second category, when start falls in the first transaction and end falls in
    //the second transaction, totalMaxProfit = (soldPrice1 - boughtPrice1) + (soldPrice2 - boughtPrice2)
    //										 = (soldPrice1 - boughtPrice2) + (soldPrice2 - boughtPrice1)
    //	boughtPrice1   start   soldPrice1   boughtPrice2   end  soldPrice2
    //To maximize soldPrice1 - boughtPrice2, we need to find the max Loss between
    //start and end. soldPrice2 is essentially the max price after end,
    //and boughtPrice1 is essentially the min price before start.


    //O(n) time and O(1) space, DP, easier to come up with in interview
    //Compute each state for the first l days:
    //buy:          dp[l][0] = max(-a[l-1], d[l-1][0])
    //buy, sell:    dp[l][1] = max(dp[l-1][0] + a[l-1], dp[l-1][1])
    //b, s, b:      dp[l][2] = max(dp[l-1][1] - a[l-1], dp[l-1][2])
    //b, s, b, s:   dp[l][3] = max(dp[l-1][2] + a[l-1], dp[l-1][3])
    //Note that if for the first day we choose the initial values like below,
    //then the max profit of each state includes the case when buy and sell
    //happens at the same day(except the last day). 
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length == 0) {
            return maxProfit;
        }
        int[] dp = {-prices[0], 0, -prices[0], 0};
        for (int l = 1; l <= prices.length; ++l) {
            dp[3] = Math.max(dp[3], dp[2] + prices[l-1]);
            dp[2] = Math.max(dp[2], dp[1] - prices[l-1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[l-1]);
            dp[0] = Math.max(dp[0], -prices[l-1]);
        }
        return Math.max(maxProfit, Math.max(dp[1], dp[3]));
    }

    public static void main(String[] args) {
    	int[] test = {3, 2, 6, 5, 0, 3};
    	int result = maxProfit(test);
    	System.out.println(Arrays.toString(test) + ": " + result);
    }
}