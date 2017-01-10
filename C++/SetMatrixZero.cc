#include <iostream>
#include <vector>
using namespace std;

void setZeroes(vector<vector<int> > &matrix) {
    int m = matrix.size();
    if (m == 0)
        return;
    int n = matrix[0].size();
    if (n == 0)
        return;

    bool firstRowIsZero = false;
    for (int i = 0; i < m; ++i)
    {
        bool rowZero = false;
        for (int j = 0; j < n; ++j)
        {
            if (matrix[i][j] == 0) {
                rowZero = true;
                matrix[0][j] = 0;
            }
        }

        if (rowZero) {
            if (i == 0)
                firstRowIsZero = true;
            else {
                fill_n(matrix[i].begin(), n, 0);
            }
        }
    }

    for (int j = 0; j < n; ++j)
    {
        if (matrix[0][j] == 0) {
            for (int i = 0; i < m; ++i)
                matrix[i][j] = 0;
        }
    }

    if (firstRowIsZero)
        fill_n(matrix[0].begin(), n, 0);
}

int main(int argc, char** argv)
{
    return 0;
}
