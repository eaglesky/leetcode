#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;

//Recursive solution using DFS. 
void dfsNQueensNum(int n, int curRow, vector<bool>& cols,
        vector<bool>& diags, vector<bool>& cdiags, int& result)
{
    if (curRow == n) {
        result++;
        return;
    }

    for (int i = 0; i < n; ++i)
    {
        if (!cols[i] && !diags[curRow + i] && !cdiags[curRow-i+n-1]) {
            cols[i] = true;
            diags[curRow+i] = true;
            cdiags[curRow-i+n-1] = true;
            dfsNQueensNum(n, curRow+1, cols, diags, cdiags, result);
            cols[i] = false;
            diags[curRow+i] = false;
            cdiags[curRow-i+n-1] = false;
        }
    }
}


int totalNQueens(int n) {
    int result = 0;
    vector<bool> cols(n, false);
    vector<bool> diags(2*n-1, false);
    vector<bool> cdiags(2*n-1, false);
    dfsNQueensNum(n, 0, cols, diags, cdiags, result);
    return result;
}

int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    cout << "n = " << n << endl;
    cout << "Total =  " << totalNQueens(n) << endl;
}
