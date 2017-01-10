public class NQueens {
    
    private void setValids(int row, int col, boolean[] validCols, boolean[] validDiags, boolean[] validAntiDiags, boolean value) {
        validCols[col] = value;
        validDiags[row + col] = value;
        validAntiDiags[validCols.length - col + row - 1] = value;
    }
    
    private void dfs(int row, boolean[] validCols, boolean[] validDiags, boolean[] validAntiDiags,
            List<String> curSolution, List<List<String>> solutions, String[] solutionStrs) {
        int n = solutionStrs.length;
        if (row >= n) {
            solutions.add(new ArrayList<String>(curSolution));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (validCols[i] && validDiags[row + i] && validAntiDiags[n - i + row - 1]) {
                curSolution.add(solutionStrs[i]);
                setValids(row, i, validCols, validDiags, validAntiDiags, false);
                dfs(row+1, validCols, validDiags, validAntiDiags, curSolution, solutions, solutionStrs);
                curSolution.remove(curSolution.size() - 1);
                setValids(row, i, validCols, validDiags, validAntiDiags, true);
            }
        }
    }
            
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        String[] solutionStrs = new String[n];
        char[] emptyStrArray = new char[n];
        Arrays.fill(emptyStrArray, '.');
        for (int i = 0; i < solutionStrs.length; ++i) {
            emptyStrArray[i] = 'Q';
            solutionStrs[i] = new String(emptyStrArray);
            emptyStrArray[i] = '.';
        }
        boolean[] validCols = new boolean[n];
        Arrays.fill(validCols, true);
        boolean[] validDiags = new boolean[2*n - 1];
        Arrays.fill(validDiags, true);
        boolean[] validAntiDiags = new boolean[2*n - 1];
        Arrays.fill(validAntiDiags, true);
        dfs(0, validCols, validDiags, validAntiDiags, new ArrayList<String>(), result, solutionStrs);
        return result;
    }

    //See nqueens.cc for the iterative solution
}