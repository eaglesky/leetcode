#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;

//Recursive solution using DFS. 
void dfsNQueens(int n, vector<int>& boardNums, vector<bool>& cols,
        vector<bool>& diags, vector<bool>& cdiags, vector<vector<string> >& result)
{
    int curRow = boardNums.size();

    if (curRow == n) {
        result.push_back(vector<string>());
        for (int i = 0; i < curRow; ++i)
        {
            string cur(n, '.');
            cur[boardNums[i]] = 'Q';
            result.back().push_back(cur);
        }
        return;
    }

    for (int i = 0; i < n; ++i)
    {
        if (!cols[i] && !diags[curRow + i] && !cdiags[curRow-i+n-1]) {
            boardNums.push_back(i);
            cols[i] = true;
            diags[curRow+i] = true;
            cdiags[curRow-i+n-1] = true;
            dfsNQueens(n, boardNums, cols, diags, cdiags, result);
            cols[i] = false;
            diags[curRow+i] = false;
            cdiags[curRow-i+n-1] = false;
            boardNums.pop_back();
        }
    }
}

vector<vector<string> > solveNQueens0(int n) {
   vector<int> boardNums;
   vector<bool> cols(n, false);
   vector<bool> diags(2*n-1, false);
   vector<bool> cdiags(2*n-1, false);
   vector<vector<string> > result;
   dfsNQueens(n, boardNums, cols, diags, cdiags, result);
   return result;
}

// Iterative solution using backtracking
vector<vector<string> > solveNQueens(int n) {
   vector<int> boardNums;
   vector<bool> cols(n, false);
   vector<bool> diags(2*n-1, false);
   vector<bool> cdiags(2*n-1, false);
   vector<vector<string> > result;
   if (n == 0)
       return result;

   boardNums.push_back(0);

   while (!boardNums.empty()) {
      int curCol = boardNums.back();
      int curRow = boardNums.size() - 1;
//      cout << curRow << ", " << curCol << endl;
      if (curCol >= n) {
          boardNums.pop_back();
          if (boardNums.empty())
              break;
          curCol = boardNums.back();
          curRow = boardNums.size() - 1;
          cols[curCol] = false;
          diags[curRow+curCol] = false;
          cdiags[curRow-curCol+n-1] = false;
          boardNums.back()++;

      } else if (!cols[curCol] && !diags[curRow + curCol] && !cdiags[curRow-curCol+n-1]) {
         if (curRow == n-1) {
            result.push_back(vector<string>());
            for (int i = 0; i <= curRow; ++i)
            {
                string cur(n, '.');
                cur[boardNums[i]] = 'Q';
                result.back().push_back(cur);
            }
            boardNums.back()++;
            continue;
          }


          cols[curCol] = true;
          diags[curRow+curCol] = true;
          cdiags[curRow-curCol+n-1] = true;
          boardNums.push_back(0);
      } else
          boardNums.back()++;
   }

   return result;
}

int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    cout << "n = " << n << endl;
    vector<vector<string> > result = solveNQueens(n);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
        {
            cout << result[i][j] << endl;
        }
        cout << endl;
    }
}
