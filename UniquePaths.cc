#include <iostream>
#include <vector>
using namespace std;

//DP solution. O(m*n) time and O(n) space
int uniquePaths(int m, int n) {
    vector<int> nums(n, 0);
    if (m == 0 || n == 0)
        return 0;

    nums[0] = 1;
    for (int i = 0; i < m; ++i)
        for (int j = 0; j < n; ++j)
        {

            if (j > 0)
                nums[j] += nums[j-1];
        }
    return nums[n-1];
}

int main(int argc, char** argv)
{
    int m = 2;
    int n = 2;
    cout << uniquePaths(m, n) << endl;
    return 0;
}
