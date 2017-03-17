import java.util.*;

public class SubMatrixSum {

	//O(n^3) time and O(n^2) space
    public int[][] submatrixSum0(int[][] matrix) {
        int[][] result = new int[2][2];
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int target = 0;
        int[][] subsums = new int[matrix.length+1][matrix[0].length+1];
        for (int i = 1; i < subsums.length; ++i) {
            for (int j = 1; j < subsums[i].length; ++j) {
                subsums[i][j] = subsums[i][j-1] + subsums[i-1][j] - subsums[i-1][j-1]
                                + matrix[i-1][j-1];
            }
        }
        for (int r1 = 0; r1 < subsums.length; ++r1) {
            for (int r2 = r1 + 1; r2 < subsums.length; ++r2) {
                Map<Integer, Integer> diffToCol = new HashMap<>();
                diffToCol.put(0, 0);
                for (int c = 1; c < subsums[r2].length; ++c) {
                    int subsum = subsums[r2][c] - subsums[r1][c];
                    int diff = subsum - target;
                    Integer prevCol = diffToCol.get(diff);
                    if (prevCol != null) {
                        result[0][0] = r1;
                        result[0][1] = prevCol;
                        result[1][0] = r2 - 1;
                        result[1][1] = c - 1;
                        return result;
                    }
                    diffToCol.put(subsum, c);
                }
            }
        }
        return result;
    }

    //O(nr^2*nc) time and O(nc) space:
	//http://www.cnblogs.com/grandyang/p/5814131.html
    public int[][] submatrixSum(int[][] matrix) {
        int[][] result = new int[2][2];
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int target = 0;
        for (int r1 = 0; r1 < matrix.length; ++r1) {
        	//sums contains cumulative sums ending with current column and previous row
        	//and starting with the first row.
            int[] sums = new int[matrix[r1].length];
            for (int r2 = r1; r2 < matrix.length; ++r2) {
                Map<Integer, Integer> diffToCol = new HashMap<>();
                diffToCol.put(0, -1);
                int rowSum = 0; //cumulative sums of current row
                for (int c = 0; c < matrix[r2].length; ++c) {
                    rowSum += matrix[r2][c];
                    sums[c] += rowSum;
                    int diff = sums[c] - target;
                    Integer prevCol = diffToCol.get(diff);
                    if (prevCol != null) {
                        result[0][0] = r1;
                        result[0][1] = prevCol + 1;
                        result[1][0] = r2;
                        result[1][1] = c;
                        return result;
                    }
                    diffToCol.put(sums[c], c);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
    	SubMatrixSum solution = new SubMatrixSum();
    	int[][] matrix = new int[][] {
    		{1,1,1,1,1,1,1,1,1,1,1,-10,1,1,1,1,1,1,1,1,1,1,1}
    	};
    	int[][] result = solution.submatrixSum(matrix);
    	System.out.println(Arrays.toString(result[0]));
    	System.out.println(Arrays.toString(result[1]));
    }
}