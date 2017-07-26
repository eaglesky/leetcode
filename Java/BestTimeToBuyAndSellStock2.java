public class BestTimeToBuyAndSellStock2 {
    
    //Greedy algorithm. O(n) time and O(1) space
    //This can be proved by looking at each transaction, the max profit
    //can be achieved by adjusting the buy and sell time so that 
    //they include all the increasing segements.
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i-1]) {
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }
}