#include <deque>
#include <iostream>
#include <vector>

using namespace std;

// Recursive solution using DFS. Pay attention to this kind of
// multi-starting-points problem. Possible improvement: instead of using the
// visited array, we can use '#' to mark the visited points on the board and
// change it back after the recursive search.
//
// Note that iterative verstion of DFS is non-trivial, since in each state
// there must be a structure storing the visited information, which in this
// case, is same size of the board.
bool dfsWord(vector<vector<char> >&board, int len, pair<int, int>& pos, string& word, vector<vector<bool> >& visited)
{
    int w = pos.first;
    int l = pos.second;
    int wmax = board.size();
    int lmax = board[0].size();

    if (board[w][l] != word[len])
        return false;
    else 
        visited[w][l] = true;

    if (len == word.size() - 1) {
        return true;
    }

    pair<int, int> adjs[4] = {{w+1, l}, {w-1, l}, {w, l+1}, {w, l-1}};
    for (int i = 0; i < 4; ++i)
    {
        pair<int, int> cur = adjs[i];
        if ((cur.first >= 0) && (cur.first < wmax) && (cur.second >= 0) && (cur.second < lmax)
                 && !visited[cur.first][cur.second]) {
           if (dfsWord(board, len+1, cur, word, visited))
               return true;
        }
    }
    visited[w][l] = false;
    return false;
}

bool exist0(vector<vector<char> > &board, string word) {
    vector<vector<bool> > visited(board.size(), vector<bool>(board[0].size(), false));
    
    if (word.size() == 0)
        return false;

    for (int i = 0; i < board.size(); ++i)
    {
        for (int j = 0; j < board[0].size(); ++j)
        {
            pair<int, int> curPos(i, j);
            if (dfsWord(board, 0, curPos, word, visited))
                return true;
        }
    }

    return false;
}

// BFS is not good for this problem. 
// 1. Board = AA
//            AA,  word = "AAAA", BFS won't work.
// And to make it work, we need to modify the judging repetition part.
// 2. Not easy to store the visited points. Cannot using board to store them.


int main(int argc, char** argv)
{
    vector<vector<char> > board = {
        {'A', 'B', 'C', 'E'},
        {'S', 'F', 'C', 'S'},
        {'A', 'D', 'E', 'E'}
    };
    
    vector<string> tests = {"ABCCED", "SEE", "ABCB"};
/*    vector<vector<char> > board = {
        {'A', 'A'}
    };
    vector<string> tests = {"AAA"};*/
    for (int i = 0; i < tests.size(); ++i)
    {
        cout << exist(board, tests[i]) << endl; 
    }
    return 0;
}
