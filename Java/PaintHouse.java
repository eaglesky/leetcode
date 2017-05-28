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
}