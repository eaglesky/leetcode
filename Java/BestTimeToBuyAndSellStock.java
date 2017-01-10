public class BestTimeToBuyAndSellStock {

	//Greedy algorithm. O(n) time and O(1) space
    public int maxProfit(int[] prices) {
        int preMin = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            preMin = Math.min(preMin, price);
            maxProfit = Math.max(maxProfit, price - preMin);
        }
        return maxProfit;
    }

    //See BestTimeToBuy.cc for other approaches.
}