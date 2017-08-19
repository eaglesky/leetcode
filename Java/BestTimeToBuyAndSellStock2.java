public class BestTimeToBuyAndSellStock2 {
    
    //Greedy algorithm. O(n) time and O(1) space
    //This can be proved by looking at each transaction, and splitting it
    //into subtransactions where each one last only 1 day. The profit of
    //each transaction can all be maximized by excluding those subtransactions
    //that have negative profit, which leaving those arising segments only.
    //Therefore we can maximize the totol profit by only including those 
    //arising segments.
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