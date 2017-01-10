#include <iostream>
#include <vector>
using namespace std;

void printBoard(vector<vector<char> >& board)
{
    for (int i = 0; i < 9; ++i)
    {
        for (int j = 0; j < 9; ++j)
            cout << board[i][j];
        cout << endl;
    }
}


int subId(int i, int j)
{
    int ib = i / 3;
    int jb = j / 3;
    return ib*3 + jb;
}

void setValid (bool validCols[][9], bool validRows[][9], bool validSubs[][9],
        char c, int i, int j, bool flag)
{
    validCols[j][c-'1'] = flag;
    validRows[i][c-'1'] = flag;
    validSubs[subId(i, j)][c-'1'] = flag;
}

bool isValid(bool validCols[][9], bool validRows[][9], bool validSubs[][9], int i, int j, char c)
{
    return validRows[i][c-'1'] && validCols[j][c-'1'] && validSubs[subId(i, j)][c-'1'];    
}

bool dfsSudoku(vector<vector<char> >& board, int start, bool validCols[][9], 
        bool validRows[][9], bool validSubs[][9])
{

    if (start == 81) {
        return true;
    }

    int i = start / 9;
    int j = start % 9;

/*    cout << "(" << i << ", " << j << ")" << endl;
    printBoard(board);
    cout << endl;
    if (start == 16) {
        if (board[1][6]=='4') {
            cout << "Here!" << endl;
        } 
    }*/


    if (board[i][j] == '.') {
        for (char c = '1'; c <= '9'; ++c)
        {
            if (isValid(validCols, validRows, validSubs, i, j, c)) {
                board[i][j] = c;
                setValid(validCols, validRows, validSubs, c, i, j, false);
                if ( dfsSudoku(board, start+1, validCols, validRows, validSubs))
                    return true;

                board[i][j] = '.';
                setValid(validCols, validRows, validSubs, c, i, j, true);
            }
        }

    } else if (dfsSudoku(board, start+1, validCols, validRows, validSubs))
        return true;

    return false;
}


void solveSudoku(vector<vector<char> > &board) {
    bool validCols[9][9];
    bool validRows[9][9];
    bool validSubs[9][9];
    fill(&validCols[0][0], &validCols[0][0]+sizeof(validCols), true);
    fill(&validRows[0][0], &validRows[0][0]+sizeof(validRows), true);
    fill(&validSubs[0][0], &validSubs[0][0]+sizeof(validSubs), true);

    for (int i = 0; i < 9; ++i)
    {
        for (int j = 0; j < 9; ++j)
        {
            if (board[i][j] != '.')
                setValid(validCols, validRows, validSubs, board[i][j], i, j, false );
        }
    }

    dfsSudoku(board, 0, validCols, validRows, validSubs);
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
