public class BestTimeToBuyAndSellStockCoolDown {
    
    //DP solution, O(n) time and O(1) space.
    //The algorithm is similar to the generalization approach of BTBSS III.
    //d[i] is the max profit that sells the last transaction at day i.
    //d[i] = max(d[i-1] + p[i] - p[i-1], max{d[t], 0 <= t < i - 1})
    //If we iterate from left to right, then max{d[t]} can be computed in O(1) time.
    public int maxProfit(int[] prices) {
        int maxSellPrePre = 0, maxSellPre = 0, maxSellCur = 0;
        int preMax = 0, curMax = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (i >= 2) {
                preMax = Math.max(preMax, maxSellPrePre);
            }
            maxSellCur = Math.max(maxSellPre + prices[i] - prices[i-1], preMax);
            curMax = Math.max(curMax, maxSellCur);
            maxSellPrePre = maxSellPre;
            maxSellPre = maxSellCur;
        }
        return curMax;
    }
}