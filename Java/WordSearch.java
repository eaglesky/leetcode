public class WordSearch {
	// With additional space
	private boolean dfs(char[][] board, int r, int c, String word, int i, boolean[][] used) {
        if ((r < 0) || (r >= board.length) || (c < 0) || (c >= board[r].length) 
            || used[r][c]) {
            return false;
        }
        if (board[r][c] != word.charAt(i)) {
            return false;
        }
        if (i == word.length() - 1) {
            return true;
        }
        used[r][c] = true;
        boolean exist = dfs(board, r-1, c, word, i+1, used) || dfs(board, r, c+1, word, i+1, used)
         || dfs(board, r+1, c, word, i+1, used) || dfs(board, r, c-1, word, i+1, used);
        used[r][c] = false;
        return exist;
    }
    
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, i, j, word, 0, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Without additional space
    private boolean dfs2(char[][] board, int r, int c, String word, int i) {
        if ((r < 0) || (r >= board.length) || (c < 0) || (c >= board[r].length) 
            || (board[r][c] == '#')) {
            return false;
        }
        if (board[r][c] != word.charAt(i)) {
            return false;
        }
        if (i == word.length() - 1) {
            return true;
        }
        char character = board[r][c];
        board[r][c] = '#'; // another simpler way: board[r][c] ^= 256;
        boolean exist = dfs(board, r-1, c, word, i+1) || dfs(board, r, c+1, word, i+1)
         || dfs(board, r+1, c, word, i+1) || dfs(board, r, c-1, word, i+1);
        board[r][c] = character; // board[r][c] ^= 256
        return exist;
    }
    
    // Better implementation of above dfs function:
    private boolean dfs(char[][] board, int i, int j, String word, int wordId) {
        if (wordId >= word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length || board[i][j] != word.charAt(wordId)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '#';
        if (dfs(board, i - 1, j, word, wordId + 1) || dfs(board, i + 1, j, word, wordId + 1)
         || dfs(board, i, j - 1, word, wordId + 1) || dfs(board, i, j + 1, word, wordId + 1)) {
            return true;
        }
        board[i][j] = c;
        return false;
    }

    public boolean exist2(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}