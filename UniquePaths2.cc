#include <iostream>
#include <vector>
using namespace std;

int uniquePathsWithObstacles(vector<vector<int> > &obstacleGrid) {
    int width = obstacleGrid.size();
    if (width == 0)
        return 0;
    int length = obstacleGrid[0].size();
    if (length == 0)
        return 0;

    vector<int> nums(length, 0);
    if (obstacleGrid[0][0] == 1)
        return 0;

    nums[0] = 1;
    for (int i = 0; i < width; ++i)
        for (int j = 0; j < length; ++j)
        {
            if (obstacleGrid[i][j] == 1)
                nums[j] = 0;
            else if (j > 0)
                nums[j] += nums[j-1];
                
        }
    return nums[length-1];
}

int main(int argc, char** argv)
{
    vector<vector<int> > grid = {
        {0, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
    };

    cout << uniquePathsWithObstacles(grid) << endl;

    return 0;
}
