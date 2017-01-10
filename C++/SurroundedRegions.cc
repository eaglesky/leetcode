#include <deque>
#include <iostream>
#include <vector>
using namespace std;

bool isOnBoarder(pair<int, int>& pos, vector<vector<char> >& board)
{
    int width = board.size();
    int length = board[0].size();
    if (pos.first == 0 || pos.first == width-1 || pos.second == 0
            || pos.second == length - 1)
        return true;
    return false;
}

void solve0(vector<vector<char> > &board) {
    int width = board.size();
    if (width == 0)//Add this to prevent run-time error!
        return;
        
    int length = board[0].size();
    if (length == 0) //Add this to prevent run-tine error!
        return;
        
    bool** visited = new bool*[width];
    for (int i = 0; i < width; ++i) {
        visited[i] = new bool[length];
        fill_n(visited[i], length, false);
    }

    vector<pair<int, int> > pairs;
    for (int i = 0; i < board.size(); ++i)
    {
        for (int j = 0; j < board[0].size(); ++j)
        {
            if ((board[i][j] == 'O') && !visited[i][j]) {
                bool flipped = true;
                pairs.clear();
                pairs.push_back(make_pair(i, j));
                visited[i][j] = true;
                int p;
                for (p = 0; p < pairs.size(); ++p)
                {
                    pair<int, int> cur = pairs[p];
                    if (isOnBoarder(cur, board)) {
                        flipped = false;
                        
                    }
                    for (int t = 0; t < 4; ++t)
                    {
                        int w, l;
                        switch(t) {
                            case 0:
                                w = cur.first-1;
                                l = cur.second;
                                break;
                            case 1:
                                w = cur.first+1;
                                l = cur.second;
                                break;
                            case 2:
                                w = cur.first;
                                l = cur.second - 1;
                                break;
                            case 3:
                                w = cur.first;
                                l = cur.second + 1;
                                break;
                        }

                        if ((w >= 0) && (w <= width-1)
                        && (l >= 0) && (l <= length - 1) && (!visited[w][l]) && (board[w][l] == 'O')) {
                            pairs.push_back(make_pair(w, l));
                            visited[w][l] = true;
                        }
                    }
                }
                if (flipped) {
                    for (int t = 0; t < pairs.size(); ++t)
                    {
                        int w = pairs[t].first;
                        int l = pairs[t].second;
                        board[w][l] = 'X';
                    }
                }
            }
        }
    }

    for (int i = 0; i < width; ++i)
        delete[] visited[i];
    delete[] visited;
}


//Improved solution(start from boundary points)
void bfsBoundary(vector<vector<char> >& board, int w, int l)
{
    int width = board.size();
    int length = board[0].size();
    deque<pair<int, int> > q;
    q.push_back(make_pair(w, l));
    board[w][l] = 'B';
    while (!q.empty()) {
        pair<int, int> cur = q.front();
        q.pop_front();
        pair<int, int> adjs[4] = {{cur.first-1, cur.second}, 
            {cur.first+1, cur.second}, 
            {cur.first, cur.second-1},
            {cur.first, cur.second+1}};
        for (int i = 0; i < 4; ++i)
        {
            int adjW = adjs[i].first;
            int adjL = adjs[i].second;
            if ((adjW >= 0) && (adjW < width) && (adjL >= 0)
                    && (adjL < length) 
                    && (board[adjW][adjL] == 'O')) {
                q.push_back(make_pair(adjW, adjL));
                board[adjW][adjL] = 'B';
            }
        }
    }
}

void solve(vector<vector<char> > &board) {
    int width = board.size();
    if (width == 0) //Add this to prevent run-time error!
        return;
    int length = board[0].size();
    if  (length == 0) // Add this to prevent run-time error!
        return;

    for (int i = 0; i < length; ++i)
    {
        if (board[0][i] == 'O')
            bfsBoundary(board, 0, i);

        if (board[width-1][i] == 'O')
            bfsBoundary(board, width-1, i);
    }

    for (int i = 0; i < width; ++i)
    {
        if (board[i][0] == 'O')
            bfsBoundary(board, i, 0);
        if (board[i][length-1] == 'O')
            bfsBoundary(board, i, length-1);
    }

    for (int i = 0; i < width; ++i)
    {
        for (int j = 0; j < length; ++j)
        {
            if (board[i][j] == 'O')
                board[i][j] = 'X';
            else if (board[i][j] == 'B')
                board[i][j] = 'O';
        }
    }
    
     
}

int main(int argc, char** argv)
{
    vector<vector<char> > board = {
        {'X', 'O', 'X', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'X', 'O', 'X'}
    };
    solve(board);
    for (int i = 0; i < board.size(); ++i)
    {
        for (int j = 0; j < board[i].size(); ++j )
            cout << board[i][j] << " ";
        cout << endl;
    }
    return 0;
}
