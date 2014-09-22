#include <iostream>
#include <vector>
using namespace std;  

 bool checkUsed(char c, vector<bool>& used) {
        
          if (c != '.') {
                    if (used[c-'0'])
                        return true;
                    else
                        used[c-'0'] = true;
          }
          
          return false;
    }
    
    bool isValidSudoku(vector<vector<char> > &board) {
        
        for (int i = 0; i < 9; ++i) {
            vector<bool> used(10, false);
            for (int j = 0; j < 9; ++j)
            {
                if (checkUsed(board[i][j], used))
                    return false;
            }
        }
        
        for (int j = 0; j < 9; ++j)
        {
            vector<bool> used(10, false);
            for (int i = 0; i < 9; ++i)
            {
                if (checkUsed(board[i][j], used))
                    return false;
            }
        }
        
        for (int grid = 0; grid < 9; ++grid)
        {
            vector<bool> used(10, false);
            int gw = (grid / 3) * 3;
            int gl = (grid % 3) * 3;
            for (int i = gw; i < gw + 3; ++i)
            {
                for (int j = gl; j < gl + 3; ++j)
                {
                    if (checkUsed(board[i][j], used))
                        return false;
                }
            }
        }
        
        return true;
            
    }

int main(int argc, char** argv)
{
    return 0;
}
