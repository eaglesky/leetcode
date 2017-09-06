public class PacificAtlanticWaterFlow {
    
    //DFS solution. markValue is 1 or 2. 
    //O(mn) time and O(mn) space
    //https://discuss.leetcode.com/topic/62314/very-concise-c-solution-using-dfs-and-bit-mask
    private static void dfs(int[][] matrix, int row, int col, int[][] visited, int markValue, int preValue,
            List<int[]> result) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || (visited[row][col] & markValue) > 0
            || matrix[row][col] < preValue) {
            return;
        }
        visited[row][col] |= markValue;
        if (visited[row][col] == 3) {
            result.add(new int[]{row, col});
        }
        dfs(matrix, row-1, col, visited, markValue, matrix[row][col], result);            
        dfs(matrix, row+1, col, visited, markValue, matrix[row][col], result);       
        dfs(matrix, row, col-1, visited, markValue, matrix[row][col], result);           
        dfs(matrix, row, col+1, visited, markValue, matrix[row][col], result);
    }
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int[][] visited = new int[matrix.length][matrix[0].length];
        for (int col = 0; col < matrix[0].length; ++col) {
            dfs(matrix, 0, col, visited, 1, 0, result);
            dfs(matrix, matrix.length - 1, col, visited, 2, 0, result);
        }
        for (int row = 0; row < matrix.length; ++row) {
            dfs(matrix, row, 0, visited, 1, 0, result);
            dfs(matrix, row, matrix[0].length-1, visited, 2, 0, result);
        }
        return result;
    }

    //Slightly improvement of above DFS solution.
    private void dfs(int[][] matrix, int r, int c, int prevHeight, int[][] visited, int flag,
                    List<int[]> result) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length
            || visited[r][c] == flag || matrix[r][c] < prevHeight) {
            return;
        }
        if (visited[r][c] > 0) {
            result.add(new int[]{r, c});
        }
        visited[r][c] = flag;
        dfs(matrix, r-1, c, matrix[r][c], visited, flag, result);
        dfs(matrix, r+1, c, matrix[r][c], visited, flag, result);
        dfs(matrix, r, c-1, matrix[r][c], visited, flag, result);
        dfs(matrix, r, c+1, matrix[r][c], visited, flag, result);

        //No need to change the visited[r][c] back!
        //This is essentially traversing all the nodes, not paths!
    }
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int[][] visited = new int[matrix.length][matrix[0].length];
        for (int c = 0; c < matrix[0].length; ++c) {
            dfs(matrix, 0, c, 0, visited, 1, result);
        }
        for (int r = 0; r < matrix.length; ++r) {
            dfs(matrix, r, 0, 0, visited, 1, result);
        }
        for (int c = 0; c < matrix[0].length; ++c) {
            dfs(matrix, matrix.length - 1, c, 0, visited, 2, result);
        }
        for (int r = 0; r < matrix.length; ++r) {
            dfs(matrix, r, matrix[r].length - 1, 0, visited, 2, result);
        }
        return result;
    }

    //BFS solution, TBD.
}