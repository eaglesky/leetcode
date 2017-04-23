public class MaximalSquare {
    
    //DP solution. Say the matrix is m * n, then
    //O(mn) time and O(n) space
    public int maximalSquare0(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] verticalLens = new int[matrix[0].length];
        int[] preSqLens = new int[matrix[0].length];
        int[] curSqLens = new int[matrix[0].length];
        int maxLen = 0;
        for (int i = 0; i < matrix.length; ++i) {
            int preHorizontal = 0;
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == '0') {
                    preHorizontal = 0;
                    verticalLens[j] = 0;
                    curSqLens[j] = 0;
                } else {
                    preHorizontal++;
                    verticalLens[j]++;
                    curSqLens[j] = (j == 0) ? 1
                        : Math.min(Math.min(preHorizontal, verticalLens[j]), preSqLens[j-1] + 1);
                    maxLen = Math.max(maxLen, curSqLens[j]);
                }
            }
            int[] temp = preSqLens;
            preSqLens = curSqLens;
            curSqLens = temp;
        }
        return maxLen * maxLen;
    }
    
    //Another DP solution. Best algorithm and implementation.
    //https://discuss.leetcode.com/topic/15328/easy-dp-solution-in-c-with-detailed-explanations-8ms-o-n-2-time-and-o-n-space/
    //O(mn) time and O(n) space
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] lens = new int[matrix[0].length + 1];
        int maxLen = 0;
        for (int lr = 1; lr <= matrix.length; ++lr) {
            int pre = 0;
            for (int lc = 1; lc <= matrix[lr-1].length; ++lc) {
                int temp = lens[lc];
                if (matrix[lr-1][lc-1] == '0') {
                    lens[lc] = 0;
                } else {
                    lens[lc] = Math.min(Math.min(pre, lens[lc]), lens[lc-1]) + 1;
                    maxLen = Math.max(maxLen, lens[lc]);
                }
                pre = temp;
            }
        }
        return maxLen * maxLen;
    }
}