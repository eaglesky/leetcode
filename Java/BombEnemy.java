public class BombEnemy {
    
    //O(mn) time and O(n) space, if grid is m x n.
    //https://discuss.leetcode.com/topic/48565/short-o-mn-time-o-n-space-solution
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] colKills = new int[grid[0].length];
        int maxKills = 0;
        for (int i = 0; i < grid.length; ++i) {
            int rowKills = 0;
            for (int j = 0; j < grid[i].length; ++j) {
                if (j == 0 || grid[i][j-1] == 'W') {
                    rowKills = 0;
                    for (int k = j; k < grid[i].length && grid[i][k] != 'W'; ++k) {
                        rowKills += grid[i][k] == 'E' ? 1 : 0;
                    }
                }
                if (i == 0 || grid[i-1][j] == 'W') {
                    colKills[j] = 0;
                    for (int k = i; k < grid.length && grid[k][j] != 'W'; ++k) {
                        colKills[j] += grid[k][j] == 'E' ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0') {
                    maxKills = Math.max(maxKills, rowKills + colKills[j]);
                }
            }
        }
        return maxKills;
    }
    
    //Another solution is DP. Though the time complexity is the same, space complexity
    //is O(mn), and the constants are also higher than the above solution.
}