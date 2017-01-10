import java.util.*;
public class NumberOfIslands {
    
    private void checkAndPut(char[][] grid, Queue<Integer> queue, int r, int c) {
        if ((r < 0) || (r >= grid.length) || (c < 0) || (c >= grid[r].length)
        || (grid[r][c] != '1')) {
            return;
        }
        queue.offer(r*grid[0].length + c);
        grid[r][c] = '#';
    }
    
    private void bfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(r*cols + c);
        grid[r][c] = '#';
        while(!queue.isEmpty()) {
            int idx = queue.poll();
            int row = idx / cols;
            int col = idx % cols;
            checkAndPut(grid, queue, row, col-1);
            checkAndPut(grid, queue, row-1, col);
            checkAndPut(grid, queue, row, col+1);
            checkAndPut(grid, queue, row+1, col);
        }
    }
    
    public int numIslands_bfs(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
                if (grid[i][j] != '0') {
                    grid[i][j] = '1';
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int r, int c) {
        if ((r < 0) || (r >= grid.length) || (c < 0) || (c >= grid[r].length)
        || (grid[r][c] != '1')) {
            return;
        }
        grid[r][c] = '#';
        dfs(grid, r, c-1);
        dfs(grid, r-1, c);
        dfs(grid, r, c+1);
        dfs(grid, r+1, c);
    }
    
    // Time complexity for both dfs and bfs is O(mn)
    // Space complexity for both of them is O(mn)
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
                if (grid[i][j] != '0') {
                    grid[i][j] = '1';
                }
            }
        }
        return count;
    }
}