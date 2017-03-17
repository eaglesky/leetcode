public class MaxSumRectangleNoLargerK {
    
    //O(n^3logn) time and O(n^2) space.
    //Space can be reduced to O(n) but is trickier
    public int maxSumSubmatrix0(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int target = k;
        int[][] subsums = new int[matrix.length+1][matrix[0].length+1];
        for (int i = 1; i < subsums.length; ++i) {
            for (int j = 1; j < subsums[i].length; ++j) {
                subsums[i][j] = subsums[i][j-1] + subsums[i-1][j] - subsums[i-1][j-1]
                                + matrix[i-1][j-1];
            }
        }
        int maxSum = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < subsums.length; ++r1) {
            for (int r2 = r1 + 1; r2 < subsums.length; ++r2) {
                TreeMap<Integer, Integer> diffToCol = new TreeMap<>();
                diffToCol.put(0, 0);
                for (int c = 1; c < subsums[r2].length; ++c) {
                    int subsum = subsums[r2][c] - subsums[r1][c];
                    int diff = subsum - target;
                    Integer ceilingKey = diffToCol.ceilingKey(diff);
                    if (ceilingKey != null) {
                        maxSum = Math.max(maxSum, subsum - ceilingKey);
                    }
                    diffToCol.put(subsum, c);
                }
            }
        }
        return maxSum;
    }
    
    //Best solution
    //O(Min(nr, nc)^2*Max(nr, nc)log(Max(nr,nc))) time
    //O(Max(nr, nc)) space
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int target = k;
        int nr = Math.min(matrix.length, matrix[0].length);
        int nc = Math.max(matrix.length, matrix[0].length);
        int maxSum = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < nr; ++r1) {
            int[] sums = new int[nc];
            for (int r2 = r1; r2 < nr; ++r2) {
                TreeMap<Integer, Integer> diffToCol = new TreeMap<>();
                diffToCol.put(0, -1);
                int rowSum = 0;
                for (int c = 0; c < nc; ++c) {
                    rowSum += (matrix.length < matrix[0].length) ? matrix[r2][c] : matrix[c][r2];
                    sums[c] += rowSum;
                    int diff = sums[c] - target;
                    Integer ceilingSum = diffToCol.ceilingKey(diff);
                    if (ceilingSum != null) {
                        maxSum = Math.max(maxSum, sums[c] - ceilingSum);
                    }
                    diffToCol.put(sums[c], c);
                }
            }
        }
        return maxSum;
    }
}