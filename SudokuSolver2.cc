#include <iostream>
#include <vector>
using namespace std;

//Improved solution of the one in SudokuSolver1.cc
bool dfsSudoku(vector<vector<char> >& board, int start, vector<vector<bool> >& rowUsed, vector<vector<bool> >& colUsed, 
vector<vector<bool> >& squareUsed)
{
    int n = board.size();
    int i = start;
    for (; (i < n*n) && (board[i/n][i%n] != '.'); ++i);
    
    if (i < n*n) {
        int r = i / n;
        int c = i % n;
        for (int j = 1; j <= 9; ++j)
        {
            if (!rowUsed[r][j-1] && !colUsed[c][j-1] && !squareUsed[r/3 * 3 + c/3][j-1]) {
                rowUsed[r][j-1] = true;
                colUsed[c][j-1] = true;
                squareUsed[r/3 * 3 + c/3][j-1] = true;
                board[r][c] = j + '0';
                if (dfsSudoku(board, i+1, rowUsed, colUsed, squareUsed))
                    return true;
                board[r][c] = '.';
                rowUsed[r][j-1] = false;
                colUsed[c][j-1] = false;
                squareUsed[r/3 * 3 + c/3][j-1] = false;
            }
        }
    } else {
        return true;
    }
    
    return false;
}

void solveSudoku(vector<vector<char> > &board) {
    vector<vector<bool> > rowUsed(9, vector<bool>(9, false));
    vector<vector<bool> > colUsed(9, vector<bool>(9, false));
    vector<vector<bool> > squareUsed(9, vector<bool>(9, false));
    
    for (int i = 0; i < 9; ++i)
    {
        for (int j = 0; j < 9; ++j)
        {
            if (board[i][j] != '.') {
                int curDig = board[i][j] - '0';
                rowUsed[i][curDig-1] = true;
                colUsed[j][curDig-1] = true;
                squareUsed[i/3 * 3 + j/3][curDig-1] = true;
            }
        }
    }
    
    dfsSudoku(board, 0, rowUsed, colUsed, squareUsed);
    
}

int main(int argc, char** argv)
{
    vector<vector<char> > board = {
        {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
        {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '2', '.', '1', '.', '9', '.', '.', '.'}, 
        {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
        {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
        {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
        {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
        {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
    };
    solveSudoku(board);
    for (int i = 0; i < board.size(); ++i)
    {
        for (int j = 0; j < board[i].size(); ++j)
            cout << board[i][j];
        cout << endl;
    }

    return 0;
}
