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

    public static void main(String[] args) {
    	int[] test = {3, 2, 6, 5, 0, 3};
    	int result = maxProfit(test);
    	System.out.println(Arrays.toString(test) + ": " + result);
    }
}