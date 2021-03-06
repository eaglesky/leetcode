#include <iostream>
#include <vector>
using namespace std;

//DP solution
//O(mn) time and O(n) space
int minPathSum0(vector<vector<int> > &grid) {
    int w = grid.size();
    if (w == 0)
        return 0;
    int l = grid[0].size();
    if (l == 0)
        return 0;

    vector<int> sums(l, 0);
    sums[0] = grid[0][0];
    for (int j = 1; j < l; ++j)
        sums[j] = sums[j-1] + grid[0][j];

    for (int i = 1; i < w; ++i)
    {
        for (int j = 0; j < l; ++j)
        {
            if (j > 0)
                sums[j] = min(sums[j], sums[j-1]) + grid[i][j];
            else
                sums[j] = sums[j] + grid[i][j];
        }
    }
    return sums[l-1];

}

//Improved version of above solution
int minPathSum(vector<vector<int> > &grid) {
    
    int nr = grid.size();
    if (nr == 0)
        return 0;
    int nc = grid[0].size();
    if (nc == 0)
        return 0;
        
    vector<int> sums(nc, INT_MAX);
    sums[0] = 0;
    for (int i = 0; i < nr; ++i)
    {
        for (int j = 0; j < nc; ++j)
        {
            if (j == 0)
                sums[j] = sums[j] + grid[i][j];
            else
                sums[j] = min(sums[j-1], sums[j]) + grid[i][j];
            
        }
    }
    
    return sums[nc-1];
}

int main(int argc, char** argv)
{
    return 0;
}
