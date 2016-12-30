public class UniquePaths2 {

	//DP solution. O(mn) time and O(n) space
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int[] sums = new int[obstacleGrid[0].length];
        sums[0] = 1;
        for (int i = 0; i < obstacleGrid.length; ++i) {
            for (int j = 0; j < obstacleGrid[0].length; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    sums[j] = 0;
                } else if (j > 0) {
                    sums[j] += sums[j-1];
                }
            }
        }
        return sums[obstacleGrid[0].length - 1];
    }
}