#include <iostream>
#include <vector>
using namespace std;

void rotateCircle(vector<vector<int> > &matrix, int c)
    {
        int n = matrix.size() - 2*c;
        for (int j = 0; j < n-1; ++j)
        {
            int temp = matrix[c][j+c];
            matrix[c][j+c] = matrix[n-1-j+c][c];
            matrix[n-1-j+c][c] = matrix[n-1+c][n-1-j+c];
            matrix[n-1+c][n-1-j+c] = matrix[j+c][n-1+c];
            matrix[j+c][n-1+c] = temp;
        }
    }

//First approach: Direct simulation--rotate from outer layer to inner layer    
    void rotate0(vector<vector<int> > &matrix) {
        int n = matrix.size();
        for (int c = 0; c < n/2; ++c)
            rotateCircle(matrix, c);
    }

// Second approach: rotate by first reverse the rows and then flip around the
// diagonal
    void rotate(vector<vector<int> > &matrix) {
        int n = matrix.size();
        if (n <= 1)
            return;
            
        for (int i = 0; i < n / 2; ++i)
        {
            for (int j = 0; j < n; ++j)
                swap(matrix[i][j], matrix[n-i-1][j]);
        }
        
        for (int i = 0; i < n; ++i)
        {
            for (int j = i+1; j < n; ++j)
            {
                swap(matrix[i][j], matrix[j][i]);
            }
        }
    }
