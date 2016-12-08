public class SudokuSolver {
    
    private boolean dfs(char[][] board, int nRegion, int id, List<Set<Integer>> usedRowSets, List<Set<Integer>> usedColSets,
        List<Set<Integer>> usedRegionSets) {
        int n = board.length;
        if (id >= n*n) {
            return true;
        }
        int i = id / n;
        int j = id % n;
        if (board[i][j] != '.') {
            return dfs(board, nRegion, id + 1, usedRowSets, usedColSets, usedRegionSets);
        } else {
            for (int num = 1; num <= n; ++num) {
                int regionId = i/nRegion * nRegion + j/nRegion;
                if (!usedRowSets.get(i).contains(num) && !usedColSets.get(j).contains(num) &&
                    !usedRegionSets.get(regionId).contains(num)) {
                    board[i][j] = (char)('0' + num);
                    usedRowSets.get(i).add(num);
                    usedColSets.get(j).add(num);
                    usedRegionSets.get(regionId).add(num);
                    if (dfs(board, nRegion, id+1, usedRowSets, usedColSets, usedRegionSets)) {
                        return true;
                    }
                    board[i][j] = '.';
                    usedRowSets.get(i).remove(num);
                    usedColSets.get(j).remove(num);
                    usedRegionSets.get(regionId).remove(num);
                }
            }
            return false;
        }
    }
    
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int n = board.length;
        int nRegion = (int)Math.sqrt(n);
        List<Set<Integer>> usedRowSets = new ArrayList<>(n);
        List<Set<Integer>> usedColSets = new ArrayList<>(n);
        List<Set<Integer>> usedRegionSets = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            usedRowSets.add(new HashSet<>());
            usedColSets.add(new HashSet<>());
            usedRegionSets.add(new HashSet<>());
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    usedRowSets.get(i).add(num);
                    usedColSets.get(j).add(num);
                    usedRegionSets.get(i/nRegion * nRegion + j/nRegion).add(num);
                }
            }
        }
        dfs(board, nRegion, 0, usedRowSets, usedColSets, usedRegionSets);
    }
}