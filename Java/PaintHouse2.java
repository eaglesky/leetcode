public class PaintHouse2 {
    
    private static int findMinId(int[] costs, int excludedId) {
        int minId = -1;
        for (int i = 0; i < costs.length; ++i) {
            if (i == excludedId) {
                continue;
            }
            if (minId == -1 || costs[i] < costs[minId]) {
                minId = i;
            }
        }
        return minId;
    }
    
    //O(nk) time and O(k) space
    public int minCostII0(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int k = costs[0].length;
        if (k == 1) {
            return costs[0][0];
        }
        int[] prevCosts = new int[k];
        for (int i = 0; i < costs.length; ++i) {
            int minId = findMinId(prevCosts, -1);
            int minId2 = findMinId(prevCosts, minId);
            int minVal = prevCosts[minId];
            int minVal2 = prevCosts[minId2];
            for (int j = 0; j < k; ++j) {
                if (j != minId) {
                    prevCosts[j] = minVal + costs[i][j];
                } else {
                    prevCosts[j] = minVal2 + costs[i][j];
                }
            }
        }
        return prevCosts[findMinId(prevCosts, -1)];
    }
    
    //O(nk) time and O(1) space
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int k = costs[0].length;
        if (k == 1) {
            return costs[0][0];
        }
        int minId = 0;
        int[] minVals = new int[]{0, 0};
        for (int i = 0; i < costs.length; ++i) {
            int curMinId = -1;
            int[] curMinVals = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int j = 0; j < k; ++j) {
                int curVal = (j == minId) ? minVals[1] + costs[i][j] : minVals[0] + costs[i][j];
                if (curVal <= curMinVals[1]) {
                    curMinVals[1] = curVal;
                    if (curVal <= curMinVals[0]) {
                        curMinVals[1] = curMinVals[0];
                        curMinVals[0] = curVal;
                        curMinId = j;
                    }
                }
            }
            minId = curMinId;
            minVals = curMinVals;
        }
        return minVals[0];
    }
    
}