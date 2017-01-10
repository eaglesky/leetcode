public class Solution {
    
    //DFS solution
    void dfs(int r, int c, char[][] board) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[r].length
        || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'N';
        
        //Adding the following two if statements to prevent stack overflow
        //when there are many 0s on the boundary.
        if (c != 0 && c != board[0].length - 1) {
            dfs(r-1, c, board);
            dfs(r+1, c, board);
        }
        if (r != 0 && r != board.length - 1) {
           dfs(r, c-1, board);
           dfs(r, c+1, board); 
        }
    }
    
    public void solve0(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board[0].length; ++i) {
            dfs(0, i, board);
            if (board.length > 1) {
                dfs(board.length - 1, i, board);
            }
        }
        for (int i = 0; i < board.length; ++i) {
            dfs(i, 0, board);
            if (board[0].length > 1) {
                dfs(i, board[i].length - 1, board);
            }
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 'N') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    //BFS solution
    class Pair {
        int r;
        int c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    void checkAndPut(int r, int c, char[][] board, Deque<Pair> q) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
        || board[r][c] != 'O') {
            return;
        }    
        board[r][c] = 'N';
        q.offer(new Pair(r, c));
    }
    
    void bfs(int r, int c, char[][] board) {
        Deque<Pair> q = new ArrayDeque<>();
        checkAndPut(r, c, board, q);
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            checkAndPut(pair.r-1, pair.c, board, q);
            checkAndPut(pair.r+1, pair.c, board, q);
            checkAndPut(pair.r, pair.c-1, board, q);
            checkAndPut(pair.r, pair.c+1, board, q);
        }
    }
    
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board[0].length; ++i) {
            bfs(0, i, board);
            if (board.length > 1) {
                bfs(board.length - 1, i, board);
            }
        }
        for (int i = 0; i < board.length; ++i) {
            bfs(i, 0, board);
            if (board[0].length > 1) {
                bfs(i, board[i].length - 1, board);
            }
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 'N') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}