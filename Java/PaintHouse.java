public class PaintHouse {
    
    //DP solution, O(n) time and O(1) space
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int minRed = costs[0][0];
        int minBlue = costs[0][1];
        int minGreen = costs[0][2];
        for (int i = 1; i < costs.length; ++i) {
            int curMinRed = Math.min(minBlue, minGreen) + costs[i][0];
            int curMinBlue = Math.min(minRed, minGreen) + costs[i][1];
            int curMinGreen = Math.min(minRed, minBlue) + costs[i][2];
            minRed = curMinRed;
            minBlue = curMinBlue;
            minGreen = curMinGreen;
        }
        return Math.min(Math.min(minRed, minBlue), minGreen);
    }

    //More scalable implementation.
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int numColors = costs[0].length;
        int[] prevMinCosts = new int[numColors];
        for (int i = 0; i < costs.length; ++i) {
            int[] curMinCosts = new int[numColors];
            for (int j = 0; j < curMinCosts.length; ++j) {
                curMinCosts[j] = Integer.MAX_VALUE;
                for (int t = 0; t < prevMinCosts.length; ++t) {
                    if (t != j) {
                        curMinCosts[j] = Math.min(curMinCosts[j],
                                    costs[i][j] + prevMinCosts[t]);
                    }
                }
            }
            prevMinCosts = curMinCosts;
        }
        int result = Integer.MAX_VALUE;
        for (int minCost : prevMinCosts) {
            result = Math.min(result, minCost);
        }
        return result;
    }
}