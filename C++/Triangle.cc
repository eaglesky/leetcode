#include <iostream>
#include <vector>
using namespace std;

// DP solution. (Top-down)
// sums[i][j] = min(sums[i-1][j-1], sums[i-1][j]) + triangle[i][j]
// (j <= i, i is the row id and j is the column id)
// O(n^2) time and O(n) space (n is the number of rows)
int minimumTotal0(vector<vector<int> > &triangle) {
    int n = triangle.back().size();
    if (triangle.size() == 0)
        return 0;

    vector<int> sums(n, 0);
    sums[0] = triangle[0][0];
    for (int i = 1; i < triangle.size(); ++i)
    {
        for (int j = i; j >= 0; --j)
        {
            if (j == i)
                sums[j] = sums[j-1] + triangle[i][j];
            else if (j == 0)
                sums[j] = sums[j] + triangle[i][j];
            else
                sums[j] = min(sums[j], sums[j-1]) + triangle[i][j];
        }
    }

    int minSum = sums[0];
    for (int i = 1; i < n; ++i)
    {
        minSum = min(minSum, sums[i]);
    }

    return minSum;
}

//DP solution (bottome-up)
//sums[i][j] = min(sums[i+1][j], sums[i+1][j+1]) + triangle[i][j]
//O(n^2) time and O(n) space (n is the number of rows)
int minimumTotal(vector<vector<int> > &triangle) {
    int n = triangle.size();
    if (n == 0)
        return 0;

    vector<int> sums = triangle.back();
    for (int i = n-2; i >= 0; --i)
    {
        for (int j = 0; j <= i; ++j)
        {
            sums[j] = min(sums[j], sums[j+1]) + triangle[i][j];
        }
    }
    
    return sums[0];

}

int main(int argc, char** argv)
{
    vector<vector<int> > test = {
        {2}, 
        {3, 4},
        {6, 5, 7},
        {4, 1, 8, 3}
    };

    cout << minimumTotal(test) << endl;
    return 0;
}
